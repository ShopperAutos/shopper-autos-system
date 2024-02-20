package com.shopper.autos.system.warehouse.service.domain.dto.create;

import com.shopper.autos.system.domain.valueobject.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateWarehouseAddress {
    private final String country;
    private final String state;
    private final String city;
    private final String address;
    private final String additional;
    private final String zipCode;
    private final Coordinate coordinate;
}
