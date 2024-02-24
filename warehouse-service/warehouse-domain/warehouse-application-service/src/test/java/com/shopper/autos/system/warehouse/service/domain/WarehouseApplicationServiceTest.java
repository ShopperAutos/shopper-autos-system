package com.shopper.autos.system.warehouse.service.domain;

import com.shopper.autos.system.domain.entity.DomainPage;
import com.shopper.autos.system.warehouse.service.domain.constant.WarehouseDomainConstant;
import com.shopper.autos.system.warehouse.service.domain.dto.WarehouseList;
import com.shopper.autos.system.warehouse.service.domain.dto.command.CreateWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.query.*;
import com.shopper.autos.system.warehouse.service.domain.dto.response.FindAllWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.response.FindWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.exception.WarehouseDomainException;
import com.shopper.autos.system.warehouse.service.domain.exception.WarehouseNotFoundException;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.shopper.autos.system.warehouse.service.domain.WarehouseTestConstant.*;
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

    private Warehouse warehouse;

    private CreateWarehouseCommand createWarehouseCommand;

    private CreateWarehouseCommand wrongAvailableSpaceCreateWarehouseCommand;

    private FindAllWarehouseQuery findAllWarehouseQuery;

    private FindWarehouseQuery findWarehouseQuery;

    private FindWarehouseQuery findWrongWarehouseQuery;

    private DomainPage<Warehouse> warehousePage;


    @BeforeAll
    public void init() {
        createWarehouseCommand = WarehouseObjectFactory.createCreateWarehouseCommand(AVAILABLE_SPACE);
        wrongAvailableSpaceCreateWarehouseCommand = WarehouseObjectFactory.createCreateWarehouseCommand(INVALID_AVAILABLE_SPACE);

        //TODO: this should be instanced from each test to set the specific filters
        findAllWarehouseQuery = WarehouseObjectFactory.createFindAllWarehouseQuery();

        findWarehouseQuery = WarehouseObjectFactory.createFindWarehouseQuery(WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER);

        findWrongWarehouseQuery = WarehouseObjectFactory.createFindWarehouseQuery(WAREHOUSE_WRONG_UNIQUE_PROPERTY_IDENTIFIER);

        warehouse = WarehouseObjectFactory.createWarehouse(createWarehouseCommand,warehouseDomainMapper);

        when(warehouseRepository.findAllByParameters(
                findAllWarehouseQuery.getPage(),
                findAllWarehouseQuery.getSize(),
                findAllWarehouseQuery.getFields(),
                findAllWarehouseQuery.getSortingValue(),
                findAllWarehouseQuery.getCountry(),
                findAllWarehouseQuery.getState(),
                findAllWarehouseQuery.getCity(),
                findAllWarehouseQuery.getAddress()
        ))
                .thenReturn(new DomainPage<>(Collections.singletonList(warehouse), findAllWarehouseQuery.getPage(), findAllWarehouseQuery.getSize(), TOTAL_RESULT));
        when(warehouseRepository.findByWarehouseUniquePropertyIdentifier(WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER))
                .thenReturn(Optional.of(warehouse));
        when(warehouseRepository.findByWarehouseUniquePropertyIdentifier(WAREHOUSE_WRONG_UNIQUE_PROPERTY_IDENTIFIER))
                .thenReturn(Optional.empty());
        when(warehouseRepository.save(any(Warehouse.class))).thenReturn(warehouse);

    }

    @Test
    void givenARightCreateWarehouseCommand_whenAttemptToCreate_thenTheWarehouseShouldBeCreatedAsPending() {
        WarehouseUpdatedResponse createdWarehouseResponse = warehouseApplicationService.createWarehouse(createWarehouseCommand);
        Assertions.assertEquals(createdWarehouseResponse.getWarehouseStatus(), WarehouseStatus.PENDING);
        Assertions.assertEquals(createdWarehouseResponse.getMessage(), WarehouseDomainConstant.CREATION_SUCCESS);
        Assertions.assertNotNull(createdWarehouseResponse.getWarehouseUniquePropertyIdentifier());
    }

    //TODO: this will be tested when other business rules are created
//    @Test
//    void givenARightCreateWarehouseCommand_whenAttemptToApprove_thenShouldThrowAnException(){
//
//    }

    @Test
    void givenAWrongAvailableSpaceCreateWarehouseCommand_whenAttemptToCreate_thenShouldThrowAnException() {
        WarehouseDomainException warehouseDomainException = Assertions.assertThrows(
                WarehouseDomainException.class,
                () -> warehouseApplicationService.createWarehouse(wrongAvailableSpaceCreateWarehouseCommand)
        );
        Assertions.assertEquals(WarehouseDomainConstant.INVALID_AVAILABLE_SPACE, warehouseDomainException.getMessage());
    }

    @Test
    void givenAFindAllWarehouseQuery_whenAttemptToFindByCriteria_thenShouldReturnAPageOfFindAllWarehouseResponse(){
        List<WarehouseList> warehouseLists = Stream.of(warehouse).map(warehouseDomainMapper::warehouseToWarehouseList).toList();
        FindAllWarehouseResponse findAllWarehouseResponse = warehouseApplicationService.findAllWarehouses(findAllWarehouseQuery);
        Assertions.assertEquals(findAllWarehouseResponse.getWarehouses(),warehouseLists);
        Assertions.assertEquals(findAllWarehouseResponse.getPage(),PAGE);
        Assertions.assertEquals(findAllWarehouseResponse.getSize(),SIZE);
        Assertions.assertEquals(findAllWarehouseResponse.getTotalResult(),TOTAL_RESULT);
    }

    @Test
    void givenAFindWarehouseQuery_whenAttemptToFindByWarehouseUniquePropertyIdentifier_thenShouldReturnFindWarehouseResponse(){
        FindWarehouseResponse findWarehouseResponse = warehouseApplicationService.findWarehouseByWarehouseUniquePropertyIdentifier(findWarehouseQuery);
        Assertions.assertEquals(warehouse.getWarehouseUniquePropertyIdentifier(),findWarehouseResponse.getWarehouseUniquePropertyIdentifier());
    }

    @Test
    void givenAWrongFindWarehouseQuery_whenAttemptToFindByWarehouseUniquePropertyIdentifier_thenShouldThrowNotFoundException(){
        WarehouseNotFoundException warehouseNotFoundException = Assertions.assertThrows(
                WarehouseNotFoundException.class,
                ()-> warehouseApplicationService.findWarehouseByWarehouseUniquePropertyIdentifier(findWrongWarehouseQuery)
        );
        Assertions.assertEquals(String.format(WarehouseDomainConstant.NOT_FOUND,WAREHOUSE_WRONG_UNIQUE_PROPERTY_IDENTIFIER),warehouseNotFoundException.getMessage());
    }

    /*@Test
    void findAllWarehouses() {
        warehousePage = warehouseRepository.findAllByParameters(
                findAllWarehouseQuery.getPage(),
                findAllWarehouseQuery.getSize(),
                findAllWarehouseQuery.getFields(),
                findAllWarehouseQuery.getSortingValue(),
                findAllWarehouseQuery.getCountry(),
                findAllWarehouseQuery.getState(),
                findAllWarehouseQuery.getCity(),
                findAllWarehouseQuery.getAddress()
        );
        System.out.println(warehousePage.getPage());
    }
*/
}