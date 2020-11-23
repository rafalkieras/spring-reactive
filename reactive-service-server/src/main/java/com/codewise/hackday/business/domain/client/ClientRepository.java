package com.codewise.hackday.business.domain.client;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface ClientRepository extends ReactiveCrudRepository<Client, UUID> {

}
