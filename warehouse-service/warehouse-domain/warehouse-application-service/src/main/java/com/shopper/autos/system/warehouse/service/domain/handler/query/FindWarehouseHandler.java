package com.shopper.autos.system.warehouse.service.domain.handler.query;

import com.shopper.autos.system.warehouse.service.domain.constant.WarehouseDomainConstant;
import com.shopper.autos.system.warehouse.service.domain.dto.query.FindWarehouseQuery;
import com.shopper.autos.system.warehouse.service.domain.dto.response.FindWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.exception.WarehouseNotFoundException;
import com.shopper.autos.system.warehouse.service.domain.mapper.WarehouseDomainMapper;
import com.shopper.autos.system.warehouse.service.domain.mediator.RequestHandler;
import com.shopper.autos.system.warehouse.service.domain.port.output.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

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
        Optional<Warehouse> warehouseResult = warehouseRepository.findByWarehouseUniquePropertyIdentifier(request.getWarehouseUniquePropertyIdentifier());
        if (warehouseResult.isEmpty()) {
            log.warn("Could not find warehouse with warehouse unique property identifier: {}", request.getWarehouseUniquePropertyIdentifier());
            throw new WarehouseNotFoundException(String.format(WarehouseDomainConstant.NOT_FOUND, request.getWarehouseUniquePropertyIdentifier()));
        }
        return warehouseDomainMapper.warehouseToFindWarehouseResponse(warehouseResult.get());
    }
}
