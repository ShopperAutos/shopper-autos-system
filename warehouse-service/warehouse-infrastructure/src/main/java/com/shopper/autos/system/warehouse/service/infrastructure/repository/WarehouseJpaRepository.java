package com.shopper.autos.system.warehouse.service.infrastructure.repository;

import com.shopper.autos.system.warehouse.service.infrastructure.entity.WarehouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WarehouseJpaRepository extends GenericJpaRepository<WarehouseEntity,UUID> {

    Optional<WarehouseEntity> findByWarehouseUniquePropertyIdentifier(String warehouseUniquePropertyIdentifier);

    @Query("SELECT w FROM WarehouseEntity w " +
            "WHERE " +
            "(:country IS NULL OR w.address.country = :country) " +
            "AND (:state IS NULL OR w.address.state = :state) " +
            "AND (:city IS NULL OR w.address.city = :city) "
    )
    Page<WarehouseEntity> findAllByParametersAndSort(
            @Param("country") String country,
            @Param("state") String state,
            @Param("city") String city,
            Pageable pageable
    );
}
