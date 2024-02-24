package com.shopper.autos.system.warehouse.service.domain.mapper;

import com.shopper.autos.system.domain.entity.DomainPage;
import com.shopper.autos.system.warehouse.service.domain.dto.WarehouseAddressResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.CreateWarehouseAddress;
import com.shopper.autos.system.warehouse.service.domain.dto.command.CreateWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.response.FindAllWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.response.FindWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.WarehouseList;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseAddress;

import java.util.UUID;
import java.util.stream.Collectors;

public class WarehouseDomainMapper {
    public Warehouse createWarehouseCommandToWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        return Warehouse.builder()
                .warehouseUniquePropertyIdentifier(createWarehouseCommand.getWarehouseUniquePropertyIdentifier())
                .address(createWarehouseAddressToWarehouseAddress(createWarehouseCommand.getLocationAddress()))
                .maxCapacity(createWarehouseCommand.getMaxCapacity())
                .availableSpace(createWarehouseCommand.getAvailableSpace())
                .build();
    }

    public WarehouseUpdatedResponse warehouseToWarehouseUpdatedResponse(Warehouse warehouse, String message) {
        return WarehouseUpdatedResponse.builder()
                .warehouseUniquePropertyIdentifier(warehouse.getWarehouseUniquePropertyIdentifier())
                .warehouseStatus(warehouse.getWarehouseStatus())
                .message(message)
                .build();
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

    public WarehouseList warehouseToWarehouseList(Warehouse warehouse) {
        return WarehouseList.builder()
                .warehouseUniquePropertyIdentifier(warehouse.getWarehouseUniquePropertyIdentifier())
                .maxCapacity(warehouse.getMaxCapacity())
                .availableSpace(warehouse.getAvailableSpace())
                .city(warehouse.getAddress().getCity())
                .address(warehouse.getAddress().getAddress())
                .build();
    }

    public FindWarehouseResponse warehouseToFindWarehouseResponse(Warehouse warehouse) {
        return FindWarehouseResponse.builder()
                .warehouseUniquePropertyIdentifier(warehouse.getWarehouseUniquePropertyIdentifier())
                .locationAddress(warehouseAddressToWarehouseAddressResponse(warehouse.getAddress()))
                .maxCapacity(warehouse.getMaxCapacity())
                .availableSpace(warehouse.getAvailableSpace())
                .warehouseStatus(warehouse.getWarehouseStatus())
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

    private WarehouseAddressResponse warehouseAddressToWarehouseAddressResponse(WarehouseAddress warehouseAddress) {
        return WarehouseAddressResponse.builder()
                .country(warehouseAddress.getCountry())
                .state(warehouseAddress.getState())
                .city(warehouseAddress.getCity())
                .address(warehouseAddress.getAddress())
                .additional(warehouseAddress.getAdditional())
                .zipCode(warehouseAddress.getZipCode())
                .coordinate(warehouseAddress.getCoordinate())
                .build();
    }
}
