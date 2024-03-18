package com.shopper.autos.system.warehouse.service.domain.handler.query;

import com.shopper.autos.system.warehouse.service.domain.dto.query.FindWarehouseQuery;
import com.shopper.autos.system.warehouse.service.domain.dto.response.FindWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.mapper.WarehouseDomainMapper;
import com.shopper.autos.system.warehouse.service.domain.mediator.RequestHandler;
import com.shopper.autos.system.warehouse.service.domain.port.output.repository.WarehouseRepository;
import com.shopper.autos.system.warehouse.service.domain.util.CommonWarehouseDomain;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FindWarehouseHandler implements RequestHandler<FindWarehouseQuery, FindWarehouseResponse> {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseDomainMapper warehouseDomainMapper;

    public FindWarehouseHandler(WarehouseRepository warehouseRepository, WarehouseDomainMapper warehouseDomainMapper) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseDomainMapper = warehouseDomainMapper;
    }

    @Override
    public FindWarehouseResponse handle(FindWarehouseQuery request) {
        Warehouse warehouseResult = CommonWarehouseDomain.findWarehouseByWarehouseUniquePropertyIdentifier(warehouseRepository,request.getWarehouseUniquePropertyIdentifier(),log);
        return warehouseDomainMapper.warehouseToFindWarehouseResponse(warehouseResult);
    }
}
