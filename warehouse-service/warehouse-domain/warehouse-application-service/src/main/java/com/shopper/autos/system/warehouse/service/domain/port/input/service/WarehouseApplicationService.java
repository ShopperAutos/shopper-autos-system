package com.shopper.autos.system.warehouse.service.domain.port.input.service;

import com.shopper.autos.system.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.create.CreateWarehouseResponse;

public interface WarehouseApplicationService {

    CreateWarehouseResponse createWarehouse(CreateWarehouseCommand createWarehouseCommand);

}
