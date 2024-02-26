package com.shopper.autos.system.warehouse.service.domain.handler.command;

import com.shopper.autos.system.warehouse.service.domain.WarehouseDomainService;
import com.shopper.autos.system.warehouse.service.domain.constant.WarehouseDomainConstant;
import com.shopper.autos.system.warehouse.service.domain.dto.command.CreateWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.shopper.autos.system.warehouse.service.domain.exception.WarehouseDomainException;
import com.shopper.autos.system.warehouse.service.domain.mapper.WarehouseDomainMapper;
import com.shopper.autos.system.warehouse.service.domain.mediator.RequestHandler;
import com.shopper.autos.system.warehouse.service.domain.port.output.publisher.WarehouseCreatedMessagePublisher;
import com.shopper.autos.system.warehouse.service.domain.port.output.repository.WarehouseRepository;

public class CreateWarehouseHandler implements RequestHandler<CreateWarehouseCommand, WarehouseUpdatedResponse> {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseDomainService warehouseDomainService;
    private final WarehouseDomainMapper warehouseDomainMapper;
    private final WarehouseCreatedMessagePublisher warehouseCreatedMessagePublisher;

    public CreateWarehouseHandler(WarehouseRepository warehouseRepository, WarehouseDomainService warehouseDomainService, WarehouseDomainMapper warehouseDomainMapper, WarehouseCreatedMessagePublisher warehouseCreatedMessagePublisher) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseDomainService = warehouseDomainService;
        this.warehouseDomainMapper = warehouseDomainMapper;
        this.warehouseCreatedMessagePublisher = warehouseCreatedMessagePublisher;
    }

    @Override
    public WarehouseUpdatedResponse handle(CreateWarehouseCommand request) {
        Warehouse warehouse = warehouseDomainMapper.createWarehouseCommandToWarehouse(request);
        WarehouseCreatedEvent warehouseCreatedEvent = warehouseDomainService.initializeWarehouse(warehouse);
        saveWarehouse(warehouse);
        //warehouseCreatedMessagePublisher.publish(warehouseCreatedEvent);
        return warehouseDomainMapper.warehouseToWarehouseUpdatedResponse(warehouseCreatedEvent.getWarehouse(), WarehouseDomainConstant.WAREHOUSE_CREATION_SUCCESS);
    }

    private void saveWarehouse(Warehouse warehouse) {
        Warehouse warehouseResult = warehouseRepository.save(warehouse);
        if (warehouseResult == null) {
            throw new WarehouseDomainException("Could not save warehouse");
        }
    }
}
