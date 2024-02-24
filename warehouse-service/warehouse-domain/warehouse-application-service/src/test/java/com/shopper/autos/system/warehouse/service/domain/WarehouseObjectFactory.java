package com.shopper.autos.system.warehouse.service.domain;

import com.shopper.autos.system.domain.valueobject.Coordinate;
import com.shopper.autos.system.domain.valueobject.SortingValue;
import com.shopper.autos.system.warehouse.service.domain.constant.WarehouseDomainConstant;
import com.shopper.autos.system.warehouse.service.domain.dto.CreateWarehouseAddress;
import com.shopper.autos.system.warehouse.service.domain.dto.command.CreateWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.query.FindAllWarehouseQuery;
import com.shopper.autos.system.warehouse.service.domain.dto.query.FindWarehouseQuery;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.mapper.WarehouseDomainMapper;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseId;

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

    public static Warehouse createWarehouse(CreateWarehouseCommand createWarehouseCommand, WarehouseDomainMapper warehouseDomainMapper) {
        Warehouse warehouse = warehouseDomainMapper.createWarehouseCommandToWarehouse(createWarehouseCommand);
        warehouse.setId(new WarehouseId(WAREHOUSE_ID));
        return warehouse;
    }

    public static WarehouseUpdatedResponse createwarehouseUpdatedResponse(Warehouse warehouse, WarehouseDomainMapper warehouseDomainMapper) {
        return warehouseDomainMapper.warehouseToWarehouseUpdatedResponse(warehouse, WarehouseDomainConstant.CREATION_SUCCESS);
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

    public static FindWarehouseQuery createFindWarehouseQuery(String warehouseUniquePropertyIdentifier){
        return FindWarehouseQuery.builder()
                .warehouseUniquePropertyIdentifier(warehouseUniquePropertyIdentifier)
                .build();
    }

}
