package com.shopper.autos.system.warehouse.service.infrastructure.adapter;

import com.shopper.autos.system.domain.entity.DomainPage;
import com.shopper.autos.system.domain.valueobject.SortingValue;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.port.output.repository.WarehouseRepository;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseId;
import com.shopper.autos.system.warehouse.service.infrastructure.entity.WarehouseEntity;
import com.shopper.autos.system.warehouse.service.infrastructure.mapper.GenericMapper;
import com.shopper.autos.system.warehouse.service.infrastructure.repository.GenericJpaRepository;
import com.shopper.autos.system.warehouse.service.infrastructure.repository.WarehouseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class WarehouseRepositoryAdapter extends BaseRepositoryAdapter<Warehouse, WarehouseEntity, WarehouseId, UUID> implements WarehouseRepository {

    private final WarehouseJpaRepository warehouseJpaRepository;

    public WarehouseRepositoryAdapter(GenericMapper<Warehouse, WarehouseEntity> mapper, GenericJpaRepository<WarehouseEntity, UUID> repository, WarehouseJpaRepository warehouseJpaRepository) {
        super(mapper, repository);
        this.warehouseJpaRepository = warehouseJpaRepository;
    }

    @Override
    public Optional<Warehouse> findByWarehouseUniquePropertyIdentifier(String warehouseUniquePropertyIdentifier) {
        return this.warehouseJpaRepository.findByWarehouseUniquePropertyIdentifier(warehouseUniquePropertyIdentifier).map(super::toDomainEntity);
    }

    @Override
    public DomainPage<Warehouse> findAllByParameters(Integer page, Integer size, List<String> fields, SortingValue sortingValue, String country, String state, String city) {
        Page<WarehouseEntity> result = warehouseJpaRepository.findAllByParametersAndSort(
                country,
                state,
                city,
                PageRequest.of(page, size)
                        .withSort((fields.isEmpty())
                                ? Sort.by(Sort.Direction.valueOf(sortingValue.name()))
                                : Sort.by(Sort.Direction.valueOf(sortingValue.name()), fields.toArray(new String[0]))
                        )
        );
        return new DomainPage<Warehouse>(
                result.getContent().stream().map(super::toDomainEntity).collect(Collectors.toList()),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements());
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
