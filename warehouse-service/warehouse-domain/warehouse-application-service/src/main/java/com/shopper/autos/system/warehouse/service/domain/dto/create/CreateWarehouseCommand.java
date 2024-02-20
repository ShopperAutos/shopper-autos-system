package com.shopper.autos.system.warehouse.service.domain.dto.create;

import com.shopper.autos.system.warehouse.service.domain.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateWarehouseCommand implements Request<CreateWarehouseResponse> {

    private final String warehouseUniquePropertyIdentifier;
    private final CreateWarehouseAddress address;
    private final Integer maxCapacity;
    private final Integer availableSpace;

}
