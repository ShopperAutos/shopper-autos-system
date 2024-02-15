package com.shopper.autos.system.warehouse.service.domain;

import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.event.WarehouseCreatedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.logging.Logger;

public class WarehouseDomainServiceImpl implements WarehouseDomainService {

    Logger log = Logger.getLogger(WarehouseDomainServiceImpl.class.getName());

    @Override
    public WarehouseCreatedEvent initializeWarehouse(Warehouse warehouse) {
        warehouse.validateWarehouse();
        warehouse.initializeWarehouse();
        log.info(String.format("Warehouse with id: %s has been initialized", warehouse.getId().getValue()));
        return new WarehouseCreatedEvent(warehouse, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public void approveWarehouse(Warehouse warehouse) {
        warehouse.approve();
        log.info(String.format("Warehouse with id: %s has been approved", warehouse.getId().getValue()));
    }

    @Override
    public void rejectWarehouse(Warehouse warehouse) {
        warehouse.reject();
        log.info(String.format("Warehouse with id: %s has been rejected", warehouse.getId().getValue()));
    }

    @Override
    public void cancelWarehouse(Warehouse warehouse) {
        warehouse.cancel();
        log.info(String.format("Warehouse with id: %s has been cancelled", warehouse.getId().getValue()));
    }
}
