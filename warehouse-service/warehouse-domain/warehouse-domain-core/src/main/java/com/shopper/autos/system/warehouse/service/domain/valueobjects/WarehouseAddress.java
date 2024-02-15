package com.shopper.autos.system.warehouse.service.domain.valueobjects;

import com.shopper.autos.system.domain.valueobject.Address;
import com.shopper.autos.system.domain.valueobject.Coordinate;

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
}
