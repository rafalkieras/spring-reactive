package com.codewise.hackday.business.web;

import com.codewise.hackday.business.domain.client.Address;
import com.codewise.hackday.business.domain.client.Client;
import com.codewise.hackday.client.AddressResource;
import com.codewise.hackday.client.ClientResource;
import com.codewise.hackday.client.ClientUpdateResource;
import org.springframework.stereotype.Component;

@Component
public class ClientAssembler {

    ClientResource toResource(Client client) {
        return ClientResource.builder()
                .withId(client.getId())
                .withName(client.getName())
                .withAddress(toResource(client.getAddress()))
                .build();
    }

    Client fromResource(ClientUpdateResource clientUpdateResource) {
        return new Client(null, clientUpdateResource.name(), fromResource(clientUpdateResource.address()));
    }

    Address fromResource(AddressResource addressResource) {
        return new Address(addressResource.city(), addressResource.zipCode(), addressResource.country());
    }

    AddressResource toResource(Address address) {
        return AddressResource.builder()
                .withCity(address.getCity())
                .withCountry(address.getCountry())
                .withZipCode(address.getZipCode())
                .build();
    }
}
