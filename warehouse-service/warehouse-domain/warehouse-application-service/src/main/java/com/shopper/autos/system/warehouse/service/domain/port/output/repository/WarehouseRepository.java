package com.shopper.autos.system.warehouse.service.domain.port.output.repository;

import com.shopper.autos.system.domain.entity.DomainPage;
import com.shopper.autos.system.domain.repository.BaseRepository;
import com.shopper.autos.system.domain.valueobject.SortingValue;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseAddress;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseId;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepository extends BaseRepository<Warehouse, WarehouseId> {

    Optional<Warehouse> findByWarehouseUniquePropertyIdentifier(String warehouseUniquePropertyIdentifier);
    DomainPage<Warehouse> findAllByParameters(Integer page,
                                Integer size,
                                List<String> fields,
                                SortingValue sortingValue,
                                String country,
                                String state,
                                String city,
                                String address);

}
