package com.shopper.autos.system.warehouse.service.domain;

import com.shopper.autos.system.domain.entity.DomainPage;
import com.shopper.autos.system.warehouse.service.domain.dto.query.FindAllWarehouseQuery;
import com.shopper.autos.system.warehouse.service.domain.dto.query.FindAllWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.mapper.WarehouseDomainMapper;
import com.shopper.autos.system.warehouse.service.domain.mediator.RequestHandler;
import com.shopper.autos.system.warehouse.service.domain.port.output.repository.WarehouseRepository;

public class FindAllWarehouseHandler implements RequestHandler<FindAllWarehouseQuery, FindAllWarehouseResponse> {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseDomainMapper warehouseDomainMapper;

    public FindAllWarehouseHandler(WarehouseRepository warehouseRepository, WarehouseDomainMapper warehouseDomainMapper) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseDomainMapper = warehouseDomainMapper;
    }

    @Override
    public FindAllWarehouseResponse handle(FindAllWarehouseQuery request) {
        DomainPage<Warehouse> warehouses = warehouseRepository
                .findAllByParameters(
                        request.getPage(),
                        request.getSize(),
                        request.getFields(),
                        request.getSortingValue(),
                        request.getCountry(),
                        request.getState(),
                        request.getCity(),
                        request.getAddress()
                );
        return warehouseDomainMapper.domainPageWarehouseToFindAllWarehouseResponse(warehouses);
    }
}
