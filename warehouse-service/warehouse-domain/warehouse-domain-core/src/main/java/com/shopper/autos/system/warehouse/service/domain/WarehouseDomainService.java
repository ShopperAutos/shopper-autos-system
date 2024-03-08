package com.shopper.autos.system.warehouse.service.domain;

import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.event.WarehouseCreatedEvent;

public interface WarehouseDomainService {
    WarehouseCreatedEvent initializeWarehouse(Warehouse warehouse);

    void updateWarehouseAvailableSpace(Warehouse warehouse, Integer availableSpace);

    void approveWarehouse(Warehouse warehouse);

    void rejectWarehouse(Warehouse warehouse);

    void cancelWarehouse(Warehouse warehouse);
}
