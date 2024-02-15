package com.shopper.autos.system.warehouse.service.domain.valueobjects;

import com.shopper.autos.system.domain.valueobject.BaseId;

import java.util.UUID;

public class WarehouseId extends BaseId<UUID> {
    public WarehouseId(UUID value) {
        super(value);
    }
}
