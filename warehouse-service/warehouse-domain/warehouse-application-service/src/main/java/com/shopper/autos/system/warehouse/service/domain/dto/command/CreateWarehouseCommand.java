package com.shopper.autos.system.warehouse.service.domain.dto.command;

import com.shopper.autos.system.warehouse.service.domain.dto.CreateWarehouseAddress;
import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.domain.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class CreateWarehouseCommand implements Request<WarehouseUpdatedResponse> {

    private final String warehouseUniquePropertyIdentifier;
    private final CreateWarehouseAddress locationAddress;
    private final Integer maxCapacity;
    private final Integer availableSpace;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateWarehouseCommand that = (CreateWarehouseCommand) o;
        return Objects.equals(warehouseUniquePropertyIdentifier, that.warehouseUniquePropertyIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warehouseUniquePropertyIdentifier);
    }
}
