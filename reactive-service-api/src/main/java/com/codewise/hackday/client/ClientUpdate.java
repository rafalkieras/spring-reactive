package com.codewise.hackday.client;

import com.codewise.hackday.Api;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Api
@Value.Immutable
public interface ClientUpdate {

    @NotBlank
    String name();

    @NotNull
    AddressResource address();
}
