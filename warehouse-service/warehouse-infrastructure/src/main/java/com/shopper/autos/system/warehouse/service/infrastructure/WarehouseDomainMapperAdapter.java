package com.shopper.autos.system.warehouse.service.infrastructure;

import com.shopper.autos.system.domain.entity.DomainPage;
import com.shopper.autos.system.warehouse.service.domain.dto.WarehouseList;
import com.shopper.autos.system.warehouse.service.domain.dto.command.CreateWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.response.FindAllWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.response.FindWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.mapper.WarehouseDomainMapper;
import org.springframework.stereotype.Component;

@Component("warehouseDomainMapperAdapter")
public class WarehouseDomainMapperAdapter implements WarehouseDomainMapper {

    private final WarehouseMapstruct warehouseMapstruct;

    public WarehouseDomainMapperAdapter(WarehouseMapstruct warehouseMapstruct) {
        this.warehouseMapstruct = warehouseMapstruct;
    }

    @Override
    public Warehouse createWarehouseCommandToWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        return warehouseMapstruct.createWarehouseCommandToWarehouse(createWarehouseCommand);
    }

    @Override
    public WarehouseUpdatedResponse warehouseToWarehouseUpdatedResponse(Warehouse warehouse, String message) {
        return null;
    }

    @Override
    public FindAllWarehouseResponse domainPageWarehouseToFindAllWarehouseResponse(DomainPage<Warehouse> warehouses) {
        return null;
    }

    @Override
    public WarehouseList warehouseToWarehouseList(Warehouse warehouse) {
        return null;
    }

    @Override
    public FindWarehouseResponse warehouseToFindWarehouseResponse(Warehouse warehouse) {
        return null;
    }
}
