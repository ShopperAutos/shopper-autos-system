package com.shopper.autos.system.warehouse.service.domain.dto.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class WarehouseList {

    private final String warehouseUniquePropertyIdentifier;
    private final Integer maxCapacity;
    private final Integer availableSpace;
    private final String city;
    private final String address;

}
