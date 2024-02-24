package com.shopper.autos.system.warehouse.service.domain.dto.response;

import com.shopper.autos.system.warehouse.service.domain.dto.WarehouseAddressResponse;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class FindWarehouseResponse {

    private final String warehouseUniquePropertyIdentifier;
    private final WarehouseAddressResponse locationAddress;
    private final Integer maxCapacity;
    private final Integer availableSpace;
    private final WarehouseStatus warehouseStatus;

}
