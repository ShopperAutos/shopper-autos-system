package com.shopper.autos.system.warehouse.service.domain.event;

import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;

import java.time.ZonedDateTime;

public class WarehouseCreatedEvent extends WarehouseEvent {
    public WarehouseCreatedEvent(Warehouse warehouse, ZonedDateTime createdAt) {
        super(warehouse, createdAt);
    }
}
