package com.shopper.autos.system.warehouse.service.domain.handler.command;

import com.shopper.autos.system.warehouse.service.domain.WarehouseDomainService;
import com.shopper.autos.system.warehouse.service.domain.constant.WarehouseDomainConstant;
import com.shopper.autos.system.warehouse.service.domain.dto.command.ApproveWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.exception.WarehouseNotFoundException;
import com.shopper.autos.system.warehouse.service.domain.mapper.WarehouseDomainMapper;
import com.shopper.autos.system.warehouse.service.domain.mediator.RequestHandler;
import com.shopper.autos.system.warehouse.service.domain.port.output.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class ApproveWarehouseHandler implements RequestHandler<ApproveWarehouseCommand, WarehouseUpdatedResponse> {

    private final WarehouseDomainService warehouseDomainService;
    private final WarehouseRepository warehouseRepository;
    private final WarehouseDomainMapper warehouseDomainMapper;

    public ApproveWarehouseHandler(WarehouseDomainService warehouseDomainService, WarehouseRepository warehouseRepository, WarehouseDomainMapper warehouseDomainMapper) {
        this.warehouseDomainService = warehouseDomainService;
        this.warehouseRepository = warehouseRepository;
        this.warehouseDomainMapper = warehouseDomainMapper;
    }

    @Override
    public WarehouseUpdatedResponse handle(ApproveWarehouseCommand request) {
        Optional<Warehouse> foundWarehouse = warehouseRepository.findByWarehouseUniquePropertyIdentifier(request.getWarehouseUniquePropertyIdentifier());
        if (foundWarehouse.isEmpty()) {
            //TODO: Change log
            log.warn("NO EXISTE");
            throw new WarehouseNotFoundException(String.format(WarehouseDomainConstant.NOT_FOUND, request.getWarehouseUniquePropertyIdentifier()));
        }
        warehouseDomainService.approveWarehouse(foundWarehouse.get());
        warehouseRepository.update(foundWarehouse.get().getId(),foundWarehouse.get());
        //TODO: Change message
        return warehouseDomainMapper.warehouseToWarehouseUpdatedResponse(foundWarehouse.get(), "ACTUALIZADO");
    }
}
