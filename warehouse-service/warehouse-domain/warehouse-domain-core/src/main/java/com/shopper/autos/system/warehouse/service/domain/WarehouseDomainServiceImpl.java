package com.shopper.autos.system.warehouse.service.domain;

import com.shopper.autos.system.domain.constant.ConfigConstant;
import com.shopper.autos.system.warehouse.service.domain.constant.WarehouseDomainConstant;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.event.WarehouseCreatedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.logging.Logger;

public class WarehouseDomainServiceImpl implements WarehouseDomainService {

    private final Logger log = Logger.getLogger(WarehouseDomainServiceImpl.class.getName());

    @Override
    public WarehouseCreatedEvent initializeWarehouse(Warehouse warehouse) {
        warehouse.validateWarehouse();
        warehouse.initializeWarehouse();
        log.info(String.format(WarehouseDomainConstant.WAREHOUSE_INITIALIZED, warehouse.getId().getValue()));
        return new WarehouseCreatedEvent(warehouse, ZonedDateTime.now(ZoneId.of(ConfigConstant.UTC_5)));
    }

    @Override
    public void updateWarehouseAvailableSpace(Warehouse warehouse, Integer availableSpace) {
        warehouse.updateAvailableSpace(availableSpace);
        log.info(String.format(WarehouseDomainConstant.WAREHOUSE_AVAILABLE_SPACE_UPDATED, warehouse.getId().getValue()));
    }

    @Override
    public void approveWarehouse(Warehouse warehouse) {
        warehouse.approve();
        log.info(String.format(WarehouseDomainConstant.WAREHOUSE_APPROVED, warehouse.getId().getValue()));
    }

    @Override
    public void rejectWarehouse(Warehouse warehouse) {
        warehouse.reject();
        log.info(String.format(WarehouseDomainConstant.WAREHOUSE_REJECTED, warehouse.getId().getValue()));
    }

    @Override
    public void cancelWarehouse(Warehouse warehouse) {
        warehouse.cancel();
        log.info(String.format(WarehouseDomainConstant.WAREHOUSE_CANCELLED, warehouse.getId().getValue()));
    }
}
