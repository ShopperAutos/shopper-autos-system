package com.shopper.autos.system.warehouse.service.infrastructure;

import com.shopper.autos.system.warehouse.service.domain.dto.command.CreateWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseId;
import com.shopper.autos.system.warehouse.service.infrastructure.entity.WarehouseEntity;
import com.shopper.autos.system.warehouse.service.infrastructure.mapper.ParentTranslator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface WarehouseMapstruct extends ParentTranslator<Warehouse, WarehouseEntity> {

    public WarehouseMapstruct INSTANCE = Mappers.getMapper(WarehouseMapstruct.class);

    @Override
    @Mappings({
            @Mapping(source = "id.value", target = "id"),
            @Mapping(source = "warehouseUniquePropertyIdentifier", target = "warehouseUniquePropertyIdentifier"),
            @Mapping(source = "address.id", target = "address.id"),
            @Mapping(source = "address.country", target = "address.country"),
            @Mapping(source = "address.state", target = "address.state"),
            @Mapping(source = "address.city", target = "address.city"),
            @Mapping(source = "address.address", target = "address.address"),
            @Mapping(source = "address.additional", target = "address.additional"),
            @Mapping(source = "address.zipCode", target = "address.zipCode"),
            @Mapping(source = "address.coordinate.latitude", target = "address.latitude"),
            @Mapping(source = "address.coordinate.longitude", target = "address.longitude"),
            @Mapping(source = "maxCapacity", target = "maxCapacity"),
            @Mapping(source = "availableSpace", target = "availableSpace"),
            @Mapping(source = "warehouseStatus", target = "warehouseStatus"),
    })
    WarehouseEntity originToDestination(Warehouse warehouse);

    @Override
    Warehouse destinationToOrigin(WarehouseEntity origin);

    Warehouse createWarehouseCommandToWarehouse(CreateWarehouseCommand warehouseCommand);

    WarehouseId map(UUID value);
}
