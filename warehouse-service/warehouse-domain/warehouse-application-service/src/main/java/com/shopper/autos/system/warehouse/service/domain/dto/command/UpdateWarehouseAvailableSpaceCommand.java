package com.shopper.autos.system.warehouse.service.domain.dto.command;

import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.warehouse.service.domain.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class UpdateWarehouseAvailableSpaceCommand implements Request<WarehouseUpdatedResponse> {
    private final String warehouseUniquePropertyIdentifier;
    private final Integer availableSpace;
}
