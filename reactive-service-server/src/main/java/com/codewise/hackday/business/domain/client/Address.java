package com.codewise.hackday.business.domain.client;

public class Address {

    private String city;

    private String zipCode;

    private String country;

    public Address() {
    }

    public Address(String city, String zipCode, String country) {
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
