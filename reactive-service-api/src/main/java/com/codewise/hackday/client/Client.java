package com.codewise.hackday.client;

import com.codewise.hackday.Api;
import org.immutables.value.Value;

import java.util.UUID;

@Api
@Value.Immutable
public interface Client {

    UUID id();

    String name();

    AddressResource address();
}
