package com.shopper.autos.system.warehouse.service.domain.entity;

import com.shopper.autos.system.domain.entity.BaseEntity;
import com.shopper.autos.system.warehouse.service.domain.constant.WarehouseDomainConstant;
import com.shopper.autos.system.warehouse.service.domain.exception.WarehouseDomainException;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseAddress;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseId;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Warehouse extends BaseEntity<WarehouseId> {

    private final String warehouseUniquePropertyIdentifier;
    private final WarehouseAddress address;
    private final List<String> vehicles;
    private final Integer maxCapacity;
    private Integer availableSpace;
    private WarehouseStatus warehouseStatus;

    private Warehouse(Builder builder) {
        super.setId(builder.warehouseId);
        warehouseUniquePropertyIdentifier = builder.warehouseUniquePropertyIdentifier;
        address = builder.address;
        vehicles = new ArrayList<>();
        maxCapacity = builder.maxCapacity;
        availableSpace = builder.availableSpace;
        warehouseStatus = builder.warehouseStatus;
    }

    public static Builder builder() {
        return new Builder();
    }


    public void initializeWarehouse() {
        super.setId(new WarehouseId(UUID.randomUUID()));
        this.warehouseStatus = WarehouseStatus.PENDING;
    }

    public void approve() {
        if (this.warehouseStatus != WarehouseStatus.PENDING)
            //TODO: change for constants
            throw new WarehouseDomainException("Warehouse is not in the right state for reject");
        this.warehouseStatus = WarehouseStatus.REJECTED;
    }

    public void reject() {
        if (this.warehouseStatus != WarehouseStatus.PENDING)
            //TODO: change for constants
            throw new WarehouseDomainException("Warehouse is not in the right state for approval");
        this.warehouseStatus = WarehouseStatus.CREATED;
    }

    public void cancel() {
        if (this.warehouseStatus != WarehouseStatus.PENDING)
            //TODO: change for constants
            throw new WarehouseDomainException("Warehouse is not in the right state for cancel");
        this.warehouseStatus = WarehouseStatus.CANCELLED;
    }

    public void updateAvailableSpace(Integer availableSpace) {
        validateAvailableSpace();
        this.availableSpace = availableSpace;
    }

    public void validateWarehouse() {
        validateInitialWarehouse();
        validateWarehouseUniquePropertyIdentifier();
        validateAvailableSpace();
    }

    public String getWarehouseUniquePropertyIdentifier() {
        return warehouseUniquePropertyIdentifier;
    }

    public WarehouseAddress getAddress() {
        return address;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public Integer getAvailableSpace() {
        return availableSpace;
    }

    public WarehouseStatus getWarehouseStatus() {
        return warehouseStatus;
    }

    private void validateInitialWarehouse() {
        if (warehouseStatus != null || getId() != null)
            throw new WarehouseDomainException("Warehouse is in wrong state for initialization");
    }

    private void validateAvailableSpace() {
        if (availableSpace < 0 || availableSpace > this.getMaxCapacity())
            throw new WarehouseDomainException(WarehouseDomainConstant.INVALID_AVAILABLE_SPACE);
    }

    private void validateWarehouseUniquePropertyIdentifier() {
        if (warehouseUniquePropertyIdentifier == null)
            throw new WarehouseDomainException("Warehouse unique property identifier cannot be null");
        if (warehouseUniquePropertyIdentifier.isEmpty())
            throw new WarehouseDomainException("Warehouse unique property identifier cannot be empty");
    }

    public static final class Builder {
        private WarehouseId warehouseId;
        private String warehouseUniquePropertyIdentifier;
        private WarehouseAddress address;
        private List<String> vehicles;
        private Integer maxCapacity;
        private Integer availableSpace;
        private WarehouseStatus warehouseStatus;

        private Builder() {
        }

        public Builder id(WarehouseId val) {
            warehouseId = val;
            return this;
        }

        public Builder warehouseUniquePropertyIdentifier(String val) {
            warehouseUniquePropertyIdentifier = val;
            return this;
        }

        public Builder address(WarehouseAddress val) {
            address = val;
            return this;
        }

        public Builder vehicles(List<String> val) {
            vehicles = val;
            return this;
        }

        public Builder maxCapacity(Integer val) {
            maxCapacity = val;
            return this;
        }

        public Builder availableSpace(Integer val) {
            availableSpace = val;
            return this;
        }

        public Builder warehouseStatus(WarehouseStatus val) {
            warehouseStatus = val;
            return this;
        }

        public Warehouse build() {
            return new Warehouse(this);
        }
    }
}
