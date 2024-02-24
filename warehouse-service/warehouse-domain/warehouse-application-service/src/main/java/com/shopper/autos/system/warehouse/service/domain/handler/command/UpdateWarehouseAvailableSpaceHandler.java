package com.shopper.autos.system.warehouse.service.domain.handler.command;

import com.shopper.autos.system.warehouse.service.domain.constant.WarehouseDomainConstant;
import com.shopper.autos.system.warehouse.service.domain.dto.command.UpdateWarehouseAvailableSpaceCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.exception.WarehouseNotFoundException;
import com.shopper.autos.system.warehouse.service.domain.mapper.WarehouseDomainMapper;
import com.shopper.autos.system.warehouse.service.domain.mediator.RequestHandler;
import com.shopper.autos.system.warehouse.service.domain.port.output.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class UpdateWarehouseAvailableSpaceHandler implements RequestHandler<UpdateWarehouseAvailableSpaceCommand, WarehouseUpdatedResponse> {

    private final WarehouseDomainMapper warehouseDomainMapper;
    private final WarehouseRepository warehouseRepository;

    public UpdateWarehouseAvailableSpaceHandler(WarehouseDomainMapper warehouseDomainMapper, WarehouseRepository warehouseRepository) {
        this.warehouseDomainMapper = warehouseDomainMapper;
        this.warehouseRepository = warehouseRepository;
    }


    @Override
    public WarehouseUpdatedResponse handle(UpdateWarehouseAvailableSpaceCommand request) {
        Optional<Warehouse> foundWarehouse = warehouseRepository.findByWarehouseUniquePropertyIdentifier(request.getWarehouseUniquePropertyIdentifier());
        //TODO: Change this piece of code for a method to apply dry principle
        if (foundWarehouse.isEmpty()) {
            //TODO: Change log
            log.warn("NO EXISTE");
            throw new WarehouseNotFoundException(String.format(WarehouseDomainConstant.NOT_FOUND, request.getWarehouseUniquePropertyIdentifier()));
        }
        warehouseRepository.update(foundWarehouse.get().getId(),foundWarehouse.get());
        return warehouseDomainMapper.warehouseToWarehouseUpdatedResponse(foundWarehouse.get(),"ESPACIO MODIFICADO");
    }
}
