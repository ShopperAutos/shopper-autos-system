package com.shopper.autos.system.domain.repository;

import com.shopper.autos.system.domain.entity.BaseEntity;
import com.shopper.autos.system.domain.valueobject.BaseId;

import java.util.Optional;

public interface BaseRepository<T extends BaseEntity<ID>, ID extends BaseId<?>> {

    T save(T entity);
    Optional<T> findById(ID id);
    void delete(ID id);

}
