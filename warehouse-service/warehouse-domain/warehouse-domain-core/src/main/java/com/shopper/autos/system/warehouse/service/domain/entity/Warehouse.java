package com.shopper.autos.system.warehouse.service.domain.entity;

import com.shopper.autos.system.domain.entity.BaseEntity;
import com.shopper.autos.system.warehouse.service.domain.constant.WarehouseDomainConstant;
import com.shopper.autos.system.warehouse.service.domain.exception.WarehouseDomainException;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseAddress;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseId;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseStatus;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Warehouse extends BaseEntity<WarehouseId> {

    private String warehouseUniquePropertyIdentifier;
    private final WarehouseAddress warehouseAddress;
    private final List<String> vehicles;
    private final Integer maxCapacity;
    private Integer availableSpace;
    private WarehouseStatus warehouseStatus;

    private Warehouse(Builder builder) {
        super.setId(builder.warehouseId);
        warehouseUniquePropertyIdentifier = builder.warehouseUniquePropertyIdentifier;
        warehouseAddress = builder.warehouseAddress;
        vehicles = builder.vehicles;
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
            throw new WarehouseDomainException(WarehouseDomainConstant.WAREHOUSE_WRONG_STATE_APPROVAL);
        this.warehouseStatus = WarehouseStatus.CREATED;
    }

    public void reject() {
        if (this.warehouseStatus != WarehouseStatus.PENDING)
            throw new WarehouseDomainException(WarehouseDomainConstant.WAREHOUSE_WRONG_STATE_REJECTION);
        this.warehouseStatus = WarehouseStatus.REJECTED;
    }

    public void cancel() {
        if (!Objects.equals(this.availableSpace, this.maxCapacity))
            throw new WarehouseDomainException(WarehouseDomainConstant.WAREHOUSE_IN_USE);
        if (this.warehouseStatus != WarehouseStatus.PENDING && this.warehouseStatus != WarehouseStatus.CREATED)
            throw new WarehouseDomainException(WarehouseDomainConstant.WAREHOUSE_WRONG_STATE_CANCELLATION);
        this.warehouseStatus = WarehouseStatus.CANCELLED;
    }

    public void updateAvailableSpace(Integer availableSpace){
        validateAvailableSpace();
        this.availableSpace = availableSpace;
    }

    public void validateWarehouse(){
        validateInitialWarehouse();
        validateWarehouseUniquePropertyIdentifier();
        validateAvailableSpace();
    }



    public String getWarehouseUniquePropertyIdentifier() {
        return warehouseUniquePropertyIdentifier;
    }

    public WarehouseAddress getWarehouseAddress() {
        return warehouseAddress;
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

    private void validateAvailableSpace(){
        if (availableSpace<0 || availableSpace > this.getMaxCapacity())
            throw new WarehouseDomainException(WarehouseDomainConstant.WAREHOUSE_INVALID_AVAILABLE_SPACE);
    }

    private void validateWarehouseUniquePropertyIdentifier() {
        if (this.warehouseUniquePropertyIdentifier == null)
            throw new WarehouseDomainException(WarehouseDomainConstant.UNIQUE_PROPERTY_IDENTIFIER_CANNOT_BE_NULL);
        if (this.warehouseUniquePropertyIdentifier.isEmpty())
            throw new WarehouseDomainException(WarehouseDomainConstant.UNIQUE_PROPERTY_IDENTIFIER_CANNOT_BE_EMPTY);
    }

    private void validateInitialWarehouse() {
        if (this.warehouseStatus != null || this.getId() != null)
            throw new WarehouseDomainException(WarehouseDomainConstant.WAREHOUSE_WRONG_STATE_INITIALIZATION);
    }


    public static final class Builder {
        private WarehouseId warehouseId;
        private String warehouseUniquePropertyIdentifier;
        private WarehouseAddress warehouseAddress;
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

        public Builder warehouseAddress(WarehouseAddress val) {
            warehouseAddress = val;
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
