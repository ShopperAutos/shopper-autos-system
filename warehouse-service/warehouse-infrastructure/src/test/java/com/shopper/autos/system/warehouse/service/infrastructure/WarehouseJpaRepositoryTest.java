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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WarehouseJpaRepositoryTest {

    @Autowired
    private WarehouseJpaRepository warehouseJpaRepository;
    private WarehouseAddressEntity warehouseAddress1;
    private WarehouseEntity warehouse1;
    private WarehouseAddressEntity warehouseAddress2;
    private WarehouseEntity warehouse2;
    private WarehouseAddressEntity warehouseAddress3;
    private WarehouseEntity warehouse3;
    private WarehouseAddressEntity warehouseAddress4;
    private WarehouseEntity warehouse4;

    private List<WarehouseEntity> warehouses;

    private static UUID ADDRESS_ID_1 = UUID.fromString("75564edd-090e-42b0-8081-3cc2cf167c34");
    private static UUID WAREHOUSE_ID_1 = UUID.fromString("75564edd-090e-42b0-8081-3cc2cf167c34");
    private static UUID ADDRESS_ID_2 = UUID.fromString("75564edd-090e-42b0-8081-3cc2cf167c35");
    private static UUID WAREHOUSE_ID_2 = UUID.fromString("75564edd-090e-42b0-8081-3cc2cf167c35");
    private static UUID ADDRESS_ID_3 = UUID.fromString("75564edd-090e-42b0-8081-3cc2cf167c36");
    private static UUID WAREHOUSE_ID_3 = UUID.fromString("75564edd-090e-42b0-8081-3cc2cf167c36");
    private static UUID ADDRESS_ID_4 = UUID.fromString("75564edd-090e-42b0-8081-3cc2cf167c37");
    private static UUID WAREHOUSE_ID_4 = UUID.fromString("75564edd-090e-42b0-8081-3cc2cf167c37");

    private static String WRONG_WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER = "000-000-000";

    private static String WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER_1 = "111-222-333";
    private static String WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER_2 = "444-555-666";
    private static String WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER_3 = "777-888-999";
    private static String WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER_4 = "000-111-222";

    @BeforeEach
    void setUp() {
        warehouses = WarehouseInfrastructureObjectFactory.generateWarehouses(10);
        warehouseAddress1 = WarehouseAddressEntity.builder()
                .id(ADDRESS_ID_1)
                .country("Colombia")
                .state("Antioquia")
                .city("Rionegro")
                .address("a")
                .additional("a")
                .zipCode("051050")
                .latitude(20.0)
                .longitude(20.0)
                .build();

        warehouse1 = WarehouseEntity.builder()
                .id(WAREHOUSE_ID_1)
                .warehouseUniquePropertyIdentifier(WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER_1)
                .warehouseStatus(WarehouseStatus.PENDING)
                .address(warehouseAddress1)
                .availableSpace(15)
                .maxCapacity(15)
                .build();

        warehouseAddress2 = WarehouseAddressEntity.builder()
                .id(ADDRESS_ID_2)
                .country("Colombia")
                .state("Antioquia")
                .city("Medellin")
                .address("b")
                .additional("b")
                .zipCode("051051")
                .latitude(21.0)
                .longitude(21.0)
                .build();
        warehouse2 = WarehouseEntity.builder()
                .id(WAREHOUSE_ID_2)
                .warehouseUniquePropertyIdentifier(WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER_2)
                .warehouseStatus(WarehouseStatus.PENDING)
                .address(warehouseAddress2)
                .availableSpace(20)
                .maxCapacity(20)
                .build();

        warehouseAddress3 = WarehouseAddressEntity.builder()
                .id(ADDRESS_ID_3)
                .country("Colombia")
                .state("Antioquia")
                .city("Bello")
                .address("c")
                .additional("c")
                .zipCode("051052")
                .latitude(22.0)
                .longitude(22.0)
                .build();
        warehouse3 = WarehouseEntity.builder()
                .id(WAREHOUSE_ID_3)
                .warehouseUniquePropertyIdentifier(WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER_3)
                .warehouseStatus(WarehouseStatus.PENDING)
                .address(warehouseAddress3)
                .availableSpace(25)
                .maxCapacity(25)
                .build();

        warehouseAddress4 = WarehouseAddressEntity.builder()
                .id(ADDRESS_ID_4)
                .country("Mexico")
                .state("Tijuana")
                .city("Bello")
                .address("d")
                .additional("d")
                .zipCode("051053")
                .latitude(23.0)
                .longitude(23.0)
                .build();
        warehouse4 = WarehouseEntity.builder()
                .id(WAREHOUSE_ID_4)
                .warehouseUniquePropertyIdentifier(WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER_4)
                .warehouseStatus(WarehouseStatus.PENDING)
                .address(warehouseAddress4)
                .availableSpace(30)
                .maxCapacity(30)
                .build();

        warehouseJpaRepository.save(warehouse1);
        warehouseJpaRepository.save(warehouse2);
        warehouseJpaRepository.save(warehouse3);
        warehouseJpaRepository.save(warehouse4);
    }

    @Test
    void givenARightWarehouseUniquePropertyIdentifier_whenAttemptToFindByWarehouseUniquePropertyIdentifier_thenShouldReturnAWarehouseEntity() {
        Optional<WarehouseEntity> foundWarehouse = warehouseJpaRepository.findByWarehouseUniquePropertyIdentifier(WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER_1);
        foundWarehouse.ifPresent(warehouseEntity -> assertEquals(warehouse1, warehouseEntity));
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
        assertEquals(3, foundByCountry.getTotalElements());
        assertEquals(1, foundByCountry.getTotalPages());
        assertEquals(warehouse1, foundByCountry.getContent().stream().findFirst().orElse(null));
    }

    @Test
    void givenAStateOnlyParameter_whenAttemptToFindAllByParametersAndSort_thenShouldReturnAllResultsByState() {
        Page<WarehouseEntity> foundByState = warehouseJpaRepository.findAllByParametersAndSort(
                null,
                "Antioquia",
                null,
                null
        );
        assertEquals(3, foundByState.getTotalElements());
        assertEquals(1, foundByState.getTotalPages());
        assertEquals(warehouse1, foundByState.getContent().stream().findFirst().orElse(null));
    }

    @Test
    void givenACityOnlyParameter_whenAttemptToFindAllByParametersAndSort_thenShouldReturnAllResultsByCity() {
        Page<WarehouseEntity> foundByCity = warehouseJpaRepository.findAllByParametersAndSort(
                null,
                null,
                "Bello",
                null
        );
        assertEquals(2, foundByCity.getTotalElements());
        assertEquals(1, foundByCity.getTotalPages());
        assertEquals(warehouse3, foundByCity.getContent().stream().findFirst().orElse(null));
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
    }

    @Test
    void findByWarehouseUniquePropertyIdentifier() {

        Page<WarehouseEntity> found = warehouseJpaRepository.findAllByParametersAndSort(
                null,
                null,
                null,
                PageRequest.of(0, 10).withSort(Sort.by(Sort.Direction.DESC, new String[]{"address.city", "address.state", "address.country"}))
        );
        assertEquals(WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER_1, 1);
    }

    @Test
    void findByAddressParams() {
    }
}