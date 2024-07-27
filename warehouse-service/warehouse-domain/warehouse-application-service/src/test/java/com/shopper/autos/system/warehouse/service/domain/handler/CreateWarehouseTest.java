package com.shopper.autos.system.warehouse.service.domain.handler;

import com.shopper.autos.system.warehouse.service.domain.WarehouseObjectFactory;
import com.shopper.autos.system.warehouse.service.domain.WarehouseTestConfiguration;
import com.shopper.autos.system.warehouse.service.domain.constant.WarehouseDomainConstant;
import com.shopper.autos.system.warehouse.service.domain.dto.command.CreateWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.exception.WarehouseDomainException;
import com.shopper.autos.system.warehouse.service.domain.mapper.WarehouseDomainMapper;
import com.shopper.autos.system.warehouse.service.domain.port.input.service.WarehouseApplicationService;
import com.shopper.autos.system.warehouse.service.domain.port.output.repository.WarehouseRepository;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.shopper.autos.system.warehouse.service.domain.WarehouseTestConstant.AVAILABLE_SPACE;
import static com.shopper.autos.system.warehouse.service.domain.WarehouseTestConstant.INVALID_AVAILABLE_SPACE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = WarehouseTestConfiguration.class)
public class CreateWarehouseTest {

    private CreateWarehouseCommand createWarehouseCommand;
    private Warehouse warehouse;
    private WarehouseUpdatedResponse warehouseUpdatedResponse;

    @Autowired
    private WarehouseApplicationService warehouseApplicationService;
    @Autowired
    private WarehouseDomainMapper warehouseDomainMapper;
    @Autowired
    private WarehouseRepository warehouseRepository;

    @BeforeAll
    void init() {




    }

    @Test
    void givenARightCreateWarehouseCommand_whenAttemptToCreate_thenTheWarehouseShouldBeCreatedAsPending() {

        createWarehouseCommand = WarehouseObjectFactory.createCreateWarehouseCommand(AVAILABLE_SPACE);
        warehouse = WarehouseObjectFactory.createWarehouse(AVAILABLE_SPACE);
        warehouseUpdatedResponse = WarehouseObjectFactory.createwarehouseUpdatedResponse(warehouse, WarehouseDomainConstant.WAREHOUSE_CREATION_SUCCESS, WarehouseStatus.PENDING);

        when(warehouseDomainMapper.createWarehouseCommandToWarehouse(createWarehouseCommand)).thenReturn(warehouse);

        when(warehouseDomainMapper.warehouseToWarehouseUpdatedResponse(warehouse, WarehouseDomainConstant.WAREHOUSE_CREATION_SUCCESS)).thenReturn(warehouseUpdatedResponse);
        when(warehouseRepository.save(any(Warehouse.class))).thenReturn(warehouse);
        WarehouseUpdatedResponse createdWarehouseResponse = warehouseApplicationService.createWarehouse(createWarehouseCommand);
        Assertions.assertEquals(WarehouseStatus.PENDING, createdWarehouseResponse.getWarehouseStatus());
        Assertions.assertEquals(createdWarehouseResponse.getMessage(), WarehouseDomainConstant.WAREHOUSE_CREATION_SUCCESS);
        Assertions.assertNotNull(createdWarehouseResponse.getWarehouseUniquePropertyIdentifier());
    }

    @Test
    void givenAWrongAvailableSpaceCreateWarehouseCommand_whenAttemptToCreate_thenShouldThrowAnException() {

        createWarehouseCommand = WarehouseObjectFactory.createCreateWarehouseCommand(INVALID_AVAILABLE_SPACE);
        warehouse = WarehouseObjectFactory.createWarehouse(INVALID_AVAILABLE_SPACE);

        when(warehouseDomainMapper.createWarehouseCommandToWarehouse(createWarehouseCommand)).thenReturn(warehouse);

        WarehouseDomainException warehouseDomainException = Assertions.assertThrows(
                WarehouseDomainException.class,
                () -> warehouseApplicationService.createWarehouse(createWarehouseCommand)
        );
        Assertions.assertEquals(WarehouseDomainConstant.WAREHOUSE_INVALID_AVAILABLE_SPACE, warehouseDomainException.getMessage());
    }

}
