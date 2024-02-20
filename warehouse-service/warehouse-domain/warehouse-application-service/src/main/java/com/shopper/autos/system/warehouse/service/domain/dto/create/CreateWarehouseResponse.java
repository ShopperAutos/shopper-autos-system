package com.shopper.autos.system.warehouse.service.domain.dto.create;

import com.shopper.autos.system.warehouse.service.domain.mediator.Request;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateWarehouseResponse {
    private final String warehouseUniquePropertyIdentifier;
    private final WarehouseStatus warehouseStatus;
    private final String message;
}
