package com.shopper.autos.system.warehouse.service.domain;

import com.shopper.autos.system.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.mediator.Mediator;
import com.shopper.autos.system.warehouse.service.domain.port.input.service.WarehouseApplicationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class WarehouseApplicationServiceImpl implements WarehouseApplicationService {

    private final Mediator mediator;

    WarehouseApplicationServiceImpl(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public CreateWarehouseResponse createWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        return mediator.send(createWarehouseCommand);
    }


}
