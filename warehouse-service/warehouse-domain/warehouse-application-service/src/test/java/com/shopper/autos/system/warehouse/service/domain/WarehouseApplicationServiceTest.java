package com.shopper.autos.system.warehouse.service.domain;

import com.shopper.autos.system.domain.entity.DomainPage;
import com.shopper.autos.system.domain.valueobject.Coordinate;
import com.shopper.autos.system.warehouse.service.domain.dto.create.CreateWarehouseAddress;
import com.shopper.autos.system.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.query.FindAllWarehouseQuery;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.mapper.WarehouseDomainMapper;
import com.shopper.autos.system.warehouse.service.domain.port.input.service.WarehouseApplicationService;
import com.shopper.autos.system.warehouse.service.domain.port.output.repository.WarehouseRepository;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = WarehouseTestConfiguration.class)
class WarehouseApplicationServiceTest {

    @Autowired
    private WarehouseApplicationService warehouseApplicationService;

    @Autowired
    private WarehouseDomainMapper warehouseDomainMapper;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private CreateWarehouseHandler createWarehouseHandler;

    private CreateWarehouseCommand createWarehouseCommand;
    private final UUID WAREHOUSE_ID = UUID.fromString("b8ff505a-d4d8-11ed-afa1-0242ac120002");

    @BeforeAll
    public void init() {
        createWarehouseCommand = CreateWarehouseCommand.builder()
                .warehouseUniquePropertyIdentifier("ABC123")
                .address(CreateWarehouseAddress.builder()
                        .country("COL")
                        .state("ANT")
                        .city("MED")
                        .address("VEREDA")
                        .zipCode("054040")
                        .additional("QWE")
                        .coordinate(new Coordinate(20.0, 20.0))
                        .build())
                .maxCapacity(15)
                .availableSpace(15)
                .build();
        Warehouse warehouse = warehouseDomainMapper.createWarehouseCommandToWarehouse(createWarehouseCommand);
        warehouse.setId(new WarehouseId(WAREHOUSE_ID));
        when(warehouseRepository.save(any(Warehouse.class))).thenReturn(warehouse);
    }


    @Test
    void createWarehouse() {
        Warehouse w = warehouseDomainMapper.createWarehouseCommandToWarehouse(createWarehouseCommand);
        CreateWarehouseResponse warehouse = warehouseApplicationService.createWarehouse(createWarehouseCommand);
        System.out.println(warehouse.getMessage());
    }
}