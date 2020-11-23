package com.codewise.hackday.business.domain.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.io.IOException;

public class ClientConverters {

    @WritingConverter
    public static class AddressWritingConverter implements Converter<Address, Json> {

        private final ObjectMapper objectMapper;

        public AddressWritingConverter(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        @Override
        public Json convert(Address address) {
            try {
                return Json.of(objectMapper.writeValueAsString(address));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @ReadingConverter
    public static class AddressReadingConverter implements Converter<Json, Address> {

        private final ObjectMapper objectMapper;

        public AddressReadingConverter(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        @Override
        public Address convert(Json json) {
            try {
                return objectMapper.readValue(json.asString(), Address.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}