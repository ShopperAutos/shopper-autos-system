package com.shopper.autos.system.domain.valueobject;

import java.util.UUID;

public class Address {
    private final UUID id;
    private final String country;
    private final String state;
    private final String city;
    private final String address;
    private final String additional;
    private final String zipCode;

    public Address(UUID id, String country, String state, String city, String address, String additional, String zipCode) {
        this.id = id;
        this.country = country;
        this.state = state;
        this.city = city;
        this.address = address;
        this.additional = additional;
        this.zipCode = zipCode;
    }

    public UUID getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getAdditional() {
        return additional;
    }

    public String getZipCode() {
        return zipCode;
    }

    //TODO: set hashcode and equals

}
