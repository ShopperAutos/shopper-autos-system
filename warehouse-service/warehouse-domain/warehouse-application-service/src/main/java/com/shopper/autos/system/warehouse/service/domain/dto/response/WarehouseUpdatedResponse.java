package com.shopper.autos.system.warehouse.service.domain.dto.response;

import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class WarehouseUpdatedResponse {
    private final String warehouseUniquePropertyIdentifier;
    private final WarehouseStatus warehouseStatus;
    private final String message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseUpdatedResponse response = (WarehouseUpdatedResponse) o;
        return Objects.equals(warehouseUniquePropertyIdentifier, response.warehouseUniquePropertyIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warehouseUniquePropertyIdentifier);
    }
}
