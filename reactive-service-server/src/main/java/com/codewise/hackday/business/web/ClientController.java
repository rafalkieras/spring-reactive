package com.codewise.hackday.business.web;

import com.codewise.hackday.business.domain.client.Client;
import com.codewise.hackday.business.domain.client.ClientRepository;
import com.codewise.hackday.client.ClientResource;
import com.codewise.hackday.client.ClientUpdateResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("client")
public class ClientController {

    private final ClientRepository clientRepository;
    private final ClientAssembler clientAssembler;

    @Autowired
    public ClientController(ClientRepository clientRepository, ClientAssembler clientAssembler) {
        this.clientRepository = clientRepository;
        this.clientAssembler = clientAssembler;
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Mono<ClientResource> getClient(@PathVariable UUID id) {
        return clientRepository.findById(id)
                .map(clientAssembler::toResource);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Mono<ClientResource> createClient(@RequestBody @Valid ClientUpdateResource clientResource) {
        Client client = clientAssembler.fromResource(clientResource);

        return clientRepository
                .save(client)
                .map(clientAssembler::toResource);
    }
}
