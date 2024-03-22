package com.shopper.autos.system.warehouse.service.domain.handler.command;

import com.shopper.autos.system.warehouse.service.domain.WarehouseDomainService;
import com.shopper.autos.system.warehouse.service.domain.dto.command.ApproveWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.mapper.WarehouseDomainMapper;
import com.shopper.autos.system.domain.mediator.RequestHandler;
import com.shopper.autos.system.warehouse.service.domain.port.output.repository.WarehouseRepository;
import com.shopper.autos.system.warehouse.service.domain.util.CommonWarehouseDomain;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseStatus;
import lombok.extern.slf4j.Slf4j;

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
        Warehouse foundWarehouse = CommonWarehouseDomain.findWarehouseByWarehouseUniquePropertyIdentifier(warehouseRepository, request.getWarehouseUniquePropertyIdentifier(), log);
        warehouseDomainService.approveWarehouse(foundWarehouse);
        warehouseRepository.updateWarehouseStatus(foundWarehouse.getId(), WarehouseStatus.CREATED);
        //TODO: Change message
        return warehouseDomainMapper.warehouseToWarehouseUpdatedResponse(foundWarehouse, "ACTUALIZADO");
    }


}
