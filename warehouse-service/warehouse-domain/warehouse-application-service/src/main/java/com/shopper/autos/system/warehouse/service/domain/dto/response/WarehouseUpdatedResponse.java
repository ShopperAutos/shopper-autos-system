package com.shopper.autos.system.warehouse.service.domain.dto.response;

import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class WarehouseUpdatedResponse {
    private final String warehouseUniquePropertyIdentifier;
    private final WarehouseStatus warehouseStatus;
    private final String message;
}
