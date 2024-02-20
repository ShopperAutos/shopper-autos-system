package com.shopper.autos.system.warehouse.service.domain.valueobjects;

import com.shopper.autos.system.domain.valueobject.Address;
import com.shopper.autos.system.domain.valueobject.Coordinate;

import java.util.Objects;
import java.util.UUID;

public class WarehouseAddress extends Address {

    private final Coordinate coordinate;

    public WarehouseAddress(UUID id, String country, String state, String city, String address, String additional, String zipCode, Coordinate coordinate) {
        super(id, country, state, city, address, additional, zipCode);
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
    //TODO: set hashcode and equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WarehouseAddress that = (WarehouseAddress) o;
        return Objects.equals(super.getCountry(), that.getCountry()) &&
                Objects.equals(super.getState(), that.getState()) &&
                Objects.equals(super.getCity(), that.getCity()) &&
                Objects.equals(super.getAddress(), that.getAddress()) &&
                Objects.equals(super.getZipCode(), that.getZipCode()) &&
                Objects.equals(coordinate, that.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coordinate);
    }
}
