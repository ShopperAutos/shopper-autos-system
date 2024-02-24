package com.shopper.autos.system.warehouse.service.domain.dto.command;

import com.shopper.autos.system.warehouse.service.domain.dto.CreateWarehouseAddress;
import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.warehouse.service.domain.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateWarehouseCommand implements Request<WarehouseUpdatedResponse> {

    private final String warehouseUniquePropertyIdentifier;
    private final CreateWarehouseAddress locationAddress;
    private final Integer maxCapacity;
    private final Integer availableSpace;

}
