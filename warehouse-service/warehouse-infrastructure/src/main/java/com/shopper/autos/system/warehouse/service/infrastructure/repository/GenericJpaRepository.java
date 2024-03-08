package com.shopper.autos.system.warehouse.service.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface GenericJpaRepository<T,ID> extends JpaRepository<T,ID> {
}
