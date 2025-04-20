package com.shopper.autos.system.infrastructure.adapter;

import com.shopper.autos.system.domain.entity.BaseEntity;
import com.shopper.autos.system.domain.repository.BaseRepository;
import com.shopper.autos.system.domain.valueobject.BaseId;
import com.shopper.autos.system.infrastructure.datasource.BaseDatasource;
import com.shopper.autos.system.infrastructure.mapper.BaseMapper;

import java.util.Optional;

public abstract class BaseRepositoryAdapter<DomainEntity extends BaseEntity<DomainID>, InfrastructureEntity, DomainID extends BaseId<InfrastructureID>, InfrastructureID, DataSource extends BaseDatasource<InfrastructureEntity, InfrastructureID>> implements BaseRepository<DomainEntity, DomainID> {

    protected final DataSource dataSource;
    protected final BaseMapper<DomainEntity, InfrastructureEntity> mapper;

    public BaseRepositoryAdapter(BaseMapper<DomainEntity, InfrastructureEntity> mapper, DataSource dataSource) {
        this.mapper = mapper;
        this.dataSource = dataSource;
    }

    @Override
    public DomainEntity save(DomainEntity entity) {
        return this.toDomainEntity(
                this.dataSource.save(this.toInfrastructureEntity(entity))
        );
    }

    @Override
    public Optional<DomainEntity> findById(DomainID id) {
        return this.dataSource.findById(id.getValue()).map(this::toDomainEntity);
    }

    @Override
    public void delete(DomainID id) {
        this.dataSource.deleteById(id.getValue());
    }

    protected InfrastructureEntity toInfrastructureEntity(DomainEntity entity) {
        return this.mapper.map(entity);
    }

    protected DomainEntity toDomainEntity(InfrastructureEntity entity) {
        return this.mapper.reverseMap(entity);
    }

}
