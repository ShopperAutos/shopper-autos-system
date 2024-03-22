package com.shopper.autos.system.warehouse.service.domain;

import com.shopper.autos.system.domain.entity.DomainPage;
import com.shopper.autos.system.domain.valueobject.Coordinate;
import com.shopper.autos.system.domain.valueobject.SortingValue;
import com.shopper.autos.system.warehouse.service.domain.constant.WarehouseDomainConstant;
import com.shopper.autos.system.warehouse.service.domain.dto.CreateWarehouseAddress;
import com.shopper.autos.system.warehouse.service.domain.dto.WarehouseAddressResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.WarehouseList;
import com.shopper.autos.system.warehouse.service.domain.dto.command.*;
import com.shopper.autos.system.warehouse.service.domain.dto.query.FindAllWarehouseQuery;
import com.shopper.autos.system.warehouse.service.domain.dto.query.FindWarehouseQuery;
import com.shopper.autos.system.warehouse.service.domain.dto.response.FindAllWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.response.FindWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.mapper.WarehouseDomainMapper;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseAddress;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseId;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseStatus;

import java.util.List;
import java.util.UUID;

import static com.shopper.autos.system.warehouse.service.domain.WarehouseTestConstant.*;

public class WarehouseObjectFactory {

    public static CreateWarehouseCommand createCreateWarehouseCommand(Integer availableSpace) {
        return CreateWarehouseCommand.builder()
                .warehouseUniquePropertyIdentifier(WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER)
                .locationAddress(CreateWarehouseAddress.builder()
                        .country(COUNTRY)
                        .state(STATE)
                        .city(CITY)
                        .address(ADDRESS)
                        .zipCode(ZIP_CODE)
                        .additional(ADDITIONAL)
                        .coordinate(new Coordinate(LATITUDE, LONGITUDE))
                        .build())
                .maxCapacity(MAX_CAPACITY)
                .availableSpace(availableSpace)
                .build();
    }

    public static Warehouse createWarehouse(Integer availableSpace) {

        return Warehouse.builder()
                .warehouseUniquePropertyIdentifier(WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER)
                .address(new WarehouseAddress(
                                UUID.randomUUID(),
                                COUNTRY,
                                STATE,
                                CITY,
                                ADDRESS,
                                ZIP_CODE,
                                ADDITIONAL,
                                new Coordinate(LATITUDE, LONGITUDE)
                        )
                )
                .maxCapacity(MAX_CAPACITY)
                .availableSpace(availableSpace)
                .build();
    }

    public static WarehouseUpdatedResponse createwarehouseUpdatedResponse(Warehouse warehouse, String message, WarehouseStatus warehouseStatus) {
        return WarehouseUpdatedResponse.builder()
                .warehouseUniquePropertyIdentifier(warehouse.getWarehouseUniquePropertyIdentifier())
                .warehouseStatus(warehouseStatus)
                .message(message)
                .build();
    }

    public static FindAllWarehouseQuery createFindAllWarehouseQuery() {
        return FindAllWarehouseQuery.builder()
                .page(PAGE)
                .size(SIZE)
                .fields(FIELDS)
                .sortingValue(SortingValue.ASC)
                .country(COUNTRY)
                .state(STATE)
                .city(CITY)
                .address(ADDRESS)
                .build();
    }

    public static FindWarehouseQuery createFindWarehouseQuery(String warehouseUniquePropertyIdentifier) {
        return FindWarehouseQuery.builder()
                .warehouseUniquePropertyIdentifier(warehouseUniquePropertyIdentifier)
                .build();
    }

    public static ApproveWarehouseCommand createApproveWarehouseCommand(String warehouseUniquePropertyIdentifier) {
        return ApproveWarehouseCommand.builder()
                .warehouseUniquePropertyIdentifier(warehouseUniquePropertyIdentifier)
                .build();
    }

    public static RejectWarehouseCommand createRejectWarehouseCommand(String warehouseUniquePropertyIdentifier) {
        return RejectWarehouseCommand.builder()
                .warehouseUniquePropertyIdentifier(warehouseUniquePropertyIdentifier)
                .build();
    }

    public static CancelWarehouseCommand createDeleteWarehouseCommand(String warehouseUniquePropertyIdentifier) {
        return CancelWarehouseCommand.builder()
                .warehouseUniquePropertyIdentifier(warehouseUniquePropertyIdentifier)
                .build();
    }

    public static UpdateWarehouseAvailableSpaceCommand createUpdateWarehouseAvailableSpaceCommand(String warehouseUniquePropertyIdentifier, Integer availableSpace) {
        return UpdateWarehouseAvailableSpaceCommand.builder()
                .warehouseUniquePropertyIdentifier(warehouseUniquePropertyIdentifier)
                .availableSpace(availableSpace)
                .build();
    }

    public static WarehouseList createWarehouseList(Warehouse warehouse) {
        return WarehouseList.builder()
                .warehouseUniquePropertyIdentifier(warehouse.getWarehouseUniquePropertyIdentifier())
                .maxCapacity(warehouse.getMaxCapacity())
                .availableSpace(warehouse.getAvailableSpace())
                .city(warehouse.getAddress().getCity())
                .address(warehouse.getAddress().getAddress())
                .build();
    }

    public static FindAllWarehouseResponse createFindAllWarehouseResponse(DomainPage<Warehouse> warehousePage) {
        return FindAllWarehouseResponse.builder()
                .warehouses(warehousePage.getContent().stream().map(WarehouseObjectFactory::createWarehouseList).toList())
                .page(warehousePage.getPage())
                .size(warehousePage.getSize())
                .totalResult(warehousePage.getTotalResult())
                .build();
    }

    public static FindWarehouseResponse createFindWarehouseResponse(Warehouse warehouse) {
        return FindWarehouseResponse.builder()
                .warehouseUniquePropertyIdentifier(warehouse.getWarehouseUniquePropertyIdentifier())
                .locationAddress(WarehouseAddressResponse.builder()
                        .country(warehouse.getAddress().getCountry())
                        .state(warehouse.getAddress().getState())
                        .city(warehouse.getAddress().getCity())
                        .address(warehouse.getAddress().getAddress())
                        .additional(warehouse.getAddress().getAdditional())
                        .zipCode(warehouse.getAddress().getZipCode())
                        .coordinate(warehouse.getAddress().getCoordinate())
                        .build())
                .maxCapacity(warehouse.getMaxCapacity())
                .availableSpace(warehouse.getAvailableSpace())
                .warehouseStatus(warehouse.getWarehouseStatus())
                .build();
    }

    public static DomainPage<Warehouse> createWarehouseDomainpage(List<Warehouse> warehouse, Integer page, Integer size) {
        return new DomainPage<>(
                warehouse,
                page,
                size,
                (long) warehouse.size()
        );
    }
}
