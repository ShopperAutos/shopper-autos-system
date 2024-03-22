package com.shopper.autos.system.warehouse.service.domain.handler.command;

import com.shopper.autos.system.warehouse.service.domain.WarehouseDomainService;
import com.shopper.autos.system.warehouse.service.domain.dto.command.UpdateWarehouseAvailableSpaceCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.mapper.WarehouseDomainMapper;
import com.shopper.autos.system.domain.mediator.RequestHandler;
import com.shopper.autos.system.warehouse.service.domain.port.output.repository.WarehouseRepository;
import com.shopper.autos.system.warehouse.service.domain.util.CommonWarehouseDomain;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateWarehouseAvailableSpaceHandler implements RequestHandler<UpdateWarehouseAvailableSpaceCommand, WarehouseUpdatedResponse> {

    private final WarehouseDomainService warehouseDomainService;
    private final WarehouseDomainMapper warehouseDomainMapper;
    private final WarehouseRepository warehouseRepository;

    public UpdateWarehouseAvailableSpaceHandler(WarehouseDomainService warehouseDomainService, WarehouseDomainMapper warehouseDomainMapper, WarehouseRepository warehouseRepository) {
        this.warehouseDomainService = warehouseDomainService;
        this.warehouseDomainMapper = warehouseDomainMapper;
        this.warehouseRepository = warehouseRepository;
    }


    @Override
    public WarehouseUpdatedResponse handle(UpdateWarehouseAvailableSpaceCommand request) {
        Warehouse foundWarehouse = CommonWarehouseDomain.findWarehouseByWarehouseUniquePropertyIdentifier(warehouseRepository, request.getWarehouseUniquePropertyIdentifier(), log);
        warehouseDomainService.updateWarehouseAvailableSpace(foundWarehouse, request.getAvailableSpace());
        warehouseRepository.updateAvailableSpace(foundWarehouse.getId(), request.getAvailableSpace());
        return warehouseDomainMapper.warehouseToWarehouseUpdatedResponse(foundWarehouse, "ESPACIO MODIFICADO");
    }
}
