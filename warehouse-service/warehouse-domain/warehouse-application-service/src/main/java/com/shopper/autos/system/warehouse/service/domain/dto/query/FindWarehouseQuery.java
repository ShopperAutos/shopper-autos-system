package com.shopper.autos.system.warehouse.service.domain.dto.query;

import com.shopper.autos.system.warehouse.service.domain.dto.response.FindWarehouseResponse;
import com.shopper.autos.system.domain.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class FindWarehouseQuery implements Request<FindWarehouseResponse> {

    private final String warehouseUniquePropertyIdentifier;

}
