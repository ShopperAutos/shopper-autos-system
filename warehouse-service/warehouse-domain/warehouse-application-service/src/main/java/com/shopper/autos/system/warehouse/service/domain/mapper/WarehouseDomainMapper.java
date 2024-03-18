package com.shopper.autos.system.warehouse.service.domain.mapper;

import com.shopper.autos.system.domain.entity.DomainPage;
import com.shopper.autos.system.warehouse.service.domain.dto.WarehouseList;
import com.shopper.autos.system.warehouse.service.domain.dto.command.CreateWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.response.FindAllWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.response.FindWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;

public interface WarehouseDomainMapper {
    Warehouse createWarehouseCommandToWarehouse(CreateWarehouseCommand createWarehouseCommand);
    WarehouseUpdatedResponse warehouseToWarehouseUpdatedResponse(Warehouse warehouse, String message);
    FindAllWarehouseResponse domainPageWarehouseToFindAllWarehouseResponse(DomainPage<Warehouse> warehouses);
    WarehouseList warehouseToWarehouseList(Warehouse warehouse);
    FindWarehouseResponse warehouseToFindWarehouseResponse(Warehouse warehouse);
}
