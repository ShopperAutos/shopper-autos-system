package com.shopper.autos.system.warehouse.service.domain.dto.command;

import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.warehouse.service.domain.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class RejectWarehouseCommand implements Request<WarehouseUpdatedResponse> {
    private final String warehouseUniquePropertyIdentifier;
}
