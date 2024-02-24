package com.shopper.autos.system.warehouse.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
@Builder
public class WarehouseList {

    private final String warehouseUniquePropertyIdentifier;
    private final Integer maxCapacity;
    private final Integer availableSpace;
    private final String city;
    private final String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseList that = (WarehouseList) o;
        return Objects.equals(warehouseUniquePropertyIdentifier, that.warehouseUniquePropertyIdentifier) && Objects.equals(maxCapacity, that.maxCapacity) && Objects.equals(availableSpace, that.availableSpace) && Objects.equals(city, that.city) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warehouseUniquePropertyIdentifier, maxCapacity, availableSpace, city, address);
    }
}
