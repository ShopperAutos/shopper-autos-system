package com.shopper.autos.system.warehouse.service.domain.util;

import com.shopper.autos.system.warehouse.service.domain.constant.WarehouseDomainConstant;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.exception.WarehouseNotFoundException;
import com.shopper.autos.system.warehouse.service.domain.port.output.repository.WarehouseRepository;
import org.slf4j.Logger;

public class CommonWarehouseDomain {

    public static Warehouse findWarehouseByWarehouseUniquePropertyIdentifier(WarehouseRepository warehouseRepository, String warehouseUniquePropertyIdentifier, Logger log) {
        return warehouseRepository.findByWarehouseUniquePropertyIdentifier(warehouseUniquePropertyIdentifier)
                .orElseThrow(() -> {
                    log.warn(String.format(WarehouseDomainConstant.WAREHOUSE_NOT_FOUND, warehouseUniquePropertyIdentifier));
                    return new WarehouseNotFoundException(String.format(WarehouseDomainConstant.WAREHOUSE_NOT_FOUND, warehouseUniquePropertyIdentifier));
                });
    }

}
