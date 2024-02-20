package com.shopper.autos.system.warehouse.service.domain.mapper;

import com.shopper.autos.system.domain.entity.DomainPage;
import com.shopper.autos.system.warehouse.service.domain.dto.create.CreateWarehouseAddress;
import com.shopper.autos.system.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.query.FindAllWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.query.WarehouseList;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseAddress;

import java.util.UUID;
import java.util.stream.Collectors;

public class WarehouseDomainMapper {
    public Warehouse createWarehouseCommandToWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        return Warehouse.builder()
                .warehouseUniquePropertyIdentifier(createWarehouseCommand.getWarehouseUniquePropertyIdentifier())
                .address(createWarehouseAddressToWarehouseAddress(createWarehouseCommand.getAddress()))
                .maxCapacity(createWarehouseCommand.getMaxCapacity())
                .availableSpace(createWarehouseCommand.getAvailableSpace())
                .build();
    }

    public CreateWarehouseResponse warehouseToCreateWarehouseResponse(Warehouse warehouse, String message) {
        return CreateWarehouseResponse.builder()
                .warehouseUniquePropertyIdentifier(warehouse.getWarehouseUniquePropertyIdentifier())
                .warehouseStatus(warehouse.getWarehouseStatus())
                .message(message)
                .build();
    }

    private WarehouseAddress createWarehouseAddressToWarehouseAddress(CreateWarehouseAddress createWarehouseAddress) {
        return new WarehouseAddress(
                UUID.randomUUID(),
                createWarehouseAddress.getCountry(),
                createWarehouseAddress.getState(),
                createWarehouseAddress.getCity(),
                createWarehouseAddress.getAddress(),
                createWarehouseAddress.getAdditional(),
                createWarehouseAddress.getZipCode(),
                createWarehouseAddress.getCoordinate()
        );
    }

    public FindAllWarehouseResponse domainPageWarehouseToFindAllWarehouseResponse(DomainPage<Warehouse> warehouses) {
        return FindAllWarehouseResponse.builder()
                .warehouses(warehouses.getContent().stream()
                        .map(this::warehouseToWarehouseList)
                        .collect(Collectors.toList()))
                .page(warehouses.getPage())
                .size(warehouses.getSize())
                .totalResult(warehouses.getTotalResult())
                .build();
    }

    private WarehouseList warehouseToWarehouseList(Warehouse warehouse) {
        return WarehouseList.builder()
                .warehouseUniquePropertyIdentifier(warehouse.getWarehouseUniquePropertyIdentifier())
                .maxCapacity(warehouse.getMaxCapacity())
                .availableSpace(warehouse.getAvailableSpace())
                .city(warehouse.getAddress().getCity())
                .address(warehouse.getAddress().getAddress())
                .build();
    }
}
