package com.shopper.autos.system.warehouse.service.infrastructure;

import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseStatus;
import com.shopper.autos.system.warehouse.service.infrastructure.entity.WarehouseAddressEntity;
import com.shopper.autos.system.warehouse.service.infrastructure.entity.WarehouseEntity;
import com.shopper.autos.system.warehouse.service.infrastructure.repository.WarehouseJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WarehouseJpaRepositoryTest {

    @Autowired
    private WarehouseJpaRepository warehouseJpaRepository;
    private List<WarehouseEntity> warehouses;
    private static final String WRONG_WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER = "000-000-000";


    @BeforeEach
    void setUp() {
        warehouses = WarehouseInfrastructureObjectFactory.getWarehouses();
        warehouseJpaRepository.saveAll(warehouses);
    }

    @Test
    void givenARightWarehouseUniquePropertyIdentifier_whenAttemptToFindByWarehouseUniquePropertyIdentifier_thenShouldReturnAWarehouseEntity() {
        Optional<WarehouseEntity> foundWarehouse = warehouseJpaRepository.findByWarehouseUniquePropertyIdentifier(warehouses.get(0).getWarehouseUniquePropertyIdentifier());
        foundWarehouse.ifPresent(warehouseEntity -> assertEquals(warehouses.get(0), warehouseEntity));
    }

    @Test
    void givenAWrongWarehouseUniquePropertyIdentifier_whenAttemptToFindByWarehouseUniquePropertyIdentifier_thenShouldReturnEmptyOptional() {
        Optional<WarehouseEntity> emptyWarehouse = warehouseJpaRepository.findByWarehouseUniquePropertyIdentifier(WRONG_WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER);
        assertEquals(Optional.empty(), emptyWarehouse);
    }

    @Test
    void givenACountryOnlyParameter_whenAttemptToFindAllByParametersAndSort_thenShouldReturnAllResultsByCountry() {
        Page<WarehouseEntity> foundByCountry = warehouseJpaRepository.findAllByParametersAndSort(
                "Colombia",
                null,
                null,
                null
        );
        Supplier<Stream<WarehouseEntity>> streamResult = () -> warehouses.stream().filter(warehouse -> warehouse.getAddress().getCountry().equals("Colombia"));
        long count = streamResult.get().count();
        assertEquals(count, foundByCountry.getTotalElements());
        assertEquals(1, foundByCountry.getTotalPages());
        assertEquals(streamResult.get().findFirst(), foundByCountry.getContent().stream().findFirst());
    }

    @Test
    void givenAStateOnlyParameter_whenAttemptToFindAllByParametersAndSort_thenShouldReturnAllResultsByState() {
        Page<WarehouseEntity> foundByState = warehouseJpaRepository.findAllByParametersAndSort(
                null,
                "Antioquia",
                null,
                null
        );
        Supplier<Stream<WarehouseEntity>> streamResult = () -> warehouses.stream().filter(warehouse -> warehouse.getAddress().getState().equals("Antioquia"));
        long results = streamResult.get().count();
        assertEquals(results, foundByState.getTotalElements());
        assertEquals(1, foundByState.getTotalPages());
        assertEquals(streamResult.get().findFirst(), foundByState.getContent().stream().findFirst());
    }

    @Test
    void givenACityOnlyParameter_whenAttemptToFindAllByParametersAndSort_thenShouldReturnAllResultsByCity() {
        Page<WarehouseEntity> foundByCity = warehouseJpaRepository.findAllByParametersAndSort(
                null,
                null,
                "Bello",
                null
        );
        Supplier<Stream<WarehouseEntity>> streamResult = () -> warehouses.stream().filter(warehouse -> warehouse.getAddress().getCity().equals("Bello"));
        assertEquals(streamResult.get().count(), foundByCity.getTotalElements());
        assertEquals(1, foundByCity.getTotalPages());
        assertEquals(streamResult.get().findFirst(), foundByCity.getContent().stream().findFirst());
    }

    @Test
    void givenAPageRequestWithOnlyAddress_whenAttemptToFindAllByParametersAndSort_thenShouldReturnAllResultsSortedByCity() {
        Page<WarehouseEntity> foundByCity = warehouseJpaRepository.findAllByParametersAndSort(
                null,
                null,
                null,
                PageRequest.of(0, 10)
                        .withSort(Sort
                                .by(Sort.Direction.DESC, new String[]{"address.city", "address.state", "address.country"})
                        )
        );
        List<WarehouseEntity> listToCompare = warehouses.stream()
                .sorted(Comparator.comparing(warehouseEntity -> warehouseEntity.getAddress().getCity(), String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            Collections.reverse(list);
                            return list;
                        }
                ));
        assertEquals(listToCompare.stream().findFirst(), foundByCity.getContent().stream().findFirst());
    }

    @Test
    void findByWarehouseUniquePropertyIdentifier() {

        Page<WarehouseEntity> found = warehouseJpaRepository.findAllByParametersAndSort(
                null,
                null,
                null,
                PageRequest.of(0, 10).withSort(Sort.by(Sort.Direction.DESC, new String[]{"address.city", "address.state", "address.country"}))
        );
        //assertEquals(warehouses.get(0).getWarehouseUniquePropertyIdentifier(), 1);
    }

    @Test
    void findByAddressParams() {
        Page<WarehouseEntity> found = warehouseJpaRepository.findAllByParametersAndSort(
                null,
                null,
                "Rionegro",
                PageRequest.of(0, 10).withSort(Sort.by(Sort.Direction.ASC, null))
        );
        System.out.println("");
    }
}