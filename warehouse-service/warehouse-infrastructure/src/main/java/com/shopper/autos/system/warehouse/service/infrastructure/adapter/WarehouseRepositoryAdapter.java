package com.shopper.autos.system.warehouse.service.infrastructure.adapter;

import com.shopper.autos.system.domain.entity.DomainPage;
import com.shopper.autos.system.domain.valueobject.SortingValue;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.port.output.repository.WarehouseRepository;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseId;
import com.shopper.autos.system.warehouse.service.infrastructure.GenericInfrastructureMapper;
import com.shopper.autos.system.warehouse.service.infrastructure.entity.WarehouseEntity;
import com.shopper.autos.system.warehouse.service.infrastructure.repository.GenericJpaRepository;
import com.shopper.autos.system.warehouse.service.infrastructure.repository.WarehouseJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class WarehouseRepositoryAdapter extends BaseRepositoryAdapter<Warehouse, WarehouseEntity, WarehouseId, UUID> implements WarehouseRepository {

    private final WarehouseJpaRepository warehouseJpaRepository;

    public WarehouseRepositoryAdapter(GenericInfrastructureMapper mapper, GenericJpaRepository<WarehouseEntity, UUID> repository, WarehouseJpaRepository warehouseJpaRepository) {
        super(mapper, repository);
        this.warehouseJpaRepository = warehouseJpaRepository;
    }


    @Override
    public Optional<Warehouse> findByWarehouseUniquePropertyIdentifier(String warehouseUniquePropertyIdentifier) {
        return super.mapper.map(this.warehouseJpaRepository.findByWarehouseUniquePropertyIdentifier(warehouseUniquePropertyIdentifier));
    }

    @Override
    public DomainPage<Warehouse> findAllByParameters(Integer page, Integer size, List<String> fields, SortingValue sortingValue, String country, String state, String city) {
        return /*new DomainPage<>()*/null;
    }

    @Override
    public DomainPage<Warehouse> findAllByAddress(String address) {
        return null;
    }

    @Override
    public Optional<Warehouse> updateAvailableSpace(String warehouseUniquePropertyIdentifier, Integer availableSpace) {
        return Optional.empty();
    }

    @Override
    public Optional<Warehouse> deleteByWarehouseUniquePropertyIdentifier(String warehouseUniquePropertyIdentifier) {
        return Optional.empty();
    }

    @Override
    public void update(WarehouseId id, Warehouse warehouse) {

    }
}
