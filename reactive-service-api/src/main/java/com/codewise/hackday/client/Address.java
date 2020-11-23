package com.codewise.hackday.client;

import com.codewise.hackday.Api;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;

@Api
@Value.Immutable
public interface Address {

    @NotBlank
    String city();

    @NotBlank
    String zipCode();

    @NotBlank
    String country();

}
