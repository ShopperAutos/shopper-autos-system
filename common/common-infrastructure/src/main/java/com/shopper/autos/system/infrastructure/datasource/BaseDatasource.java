package com.shopper.autos.system.infrastructure.datasource;

import java.util.Optional;

public interface BaseDatasource<InfrastructureEntity, InfrastructureID> {
    InfrastructureEntity save(InfrastructureEntity entity);

    Optional<InfrastructureEntity> findById(InfrastructureID id);

    void  deleteById(InfrastructureID id);
}
