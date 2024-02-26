package com.shopper.autos.system.domain.valueobject;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(country, address1.country) && Objects.equals(state, address1.state) && Objects.equals(city, address1.city) && Objects.equals(address, address1.address) && Objects.equals(zipCode, address1.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, state, city, address, zipCode);
    }
}
