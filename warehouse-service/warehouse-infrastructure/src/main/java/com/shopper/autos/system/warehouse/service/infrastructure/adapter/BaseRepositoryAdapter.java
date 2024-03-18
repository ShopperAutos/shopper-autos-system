package com.shopper.autos.system.warehouse.service.infrastructure.adapter;

import com.shopper.autos.system.domain.entity.BaseEntity;
import com.shopper.autos.system.domain.repository.BaseRepository;
import com.shopper.autos.system.domain.valueobject.BaseId;
import com.shopper.autos.system.warehouse.service.infrastructure.mapper.GenericMapper;
import com.shopper.autos.system.warehouse.service.infrastructure.repository.GenericJpaRepository;

import java.util.Optional;

public abstract class BaseRepositoryAdapter<DomainEntity extends BaseEntity<DomainID>, InfrastructureEntity, DomainID extends BaseId<InfrastructureID>, InfrastructureID> implements BaseRepository<DomainEntity, DomainID> {

    protected final GenericMapper<DomainEntity, InfrastructureEntity> mapper;
    protected final GenericJpaRepository<InfrastructureEntity, InfrastructureID> repository;

    public BaseRepositoryAdapter(GenericMapper<DomainEntity, InfrastructureEntity> mapper, GenericJpaRepository<InfrastructureEntity, InfrastructureID> repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public DomainEntity save(DomainEntity entity) {
        return this.toDomainEntity(
                this.repository.save(this.toInfrastructureEntity(entity))
        );
    }

    @Override
    public Optional<DomainEntity> findById(DomainID id) {
        return this.repository.findById(id.getValue()).map(this::toDomainEntity);
    }

    @Override
    public void delete(DomainID id) {
        this.repository.deleteById(id.getValue());
    }

    protected InfrastructureEntity toInfrastructureEntity(DomainEntity entity) {
        return this.mapper.map(entity);
    }

    protected DomainEntity toDomainEntity(InfrastructureEntity entity) {
        return this.mapper.reverseMap(entity);
    }

}
