package com.shopper.autos.system.warehouse.service.domain.dto;

import com.shopper.autos.system.domain.valueobject.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class WarehouseAddressResponse {
    private final String country;
    private final String state;
    private final String city;
    private final String address;
    private final String additional;
    private final String zipCode;
    private final Coordinate coordinate;
}
