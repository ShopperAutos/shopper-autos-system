package com.shopper.autos.system.warehouse.service.domain;

import com.shopper.autos.system.domain.entity.DomainPage;
import com.shopper.autos.system.warehouse.service.domain.constant.WarehouseDomainConstant;
import com.shopper.autos.system.warehouse.service.domain.dto.WarehouseList;
import com.shopper.autos.system.warehouse.service.domain.dto.command.*;
import com.shopper.autos.system.warehouse.service.domain.dto.query.FindAllWarehouseQuery;
import com.shopper.autos.system.warehouse.service.domain.dto.query.FindWarehouseQuery;
import com.shopper.autos.system.warehouse.service.domain.dto.response.FindAllWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.response.FindWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.exception.WarehouseDomainException;
import com.shopper.autos.system.warehouse.service.domain.exception.WarehouseNotFoundException;
import com.shopper.autos.system.warehouse.service.domain.mapper.WarehouseDomainMapper;
import com.shopper.autos.system.warehouse.service.domain.port.input.service.WarehouseApplicationService;
import com.shopper.autos.system.warehouse.service.domain.port.output.repository.WarehouseRepository;
import com.shopper.autos.system.warehouse.service.domain.util.CommonWarehouseDomain;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseId;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseStatus;
import org.junit.jupiter.api.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.shopper.autos.system.warehouse.service.domain.WarehouseTestConstant.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
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

    private ApproveWarehouseCommand approveWarehouseCommand;

    private WarehouseUpdatedResponse warehouseUpdatedResponse;

    private WarehouseList warehouseList;

    private ApproveWarehouseCommand approveWrongWarehouseCommand;

    private CancelWarehouseCommand cancelWarehouseCommand;

    private RejectWarehouseCommand rejectWarehouseCommand;

    private UpdateWarehouseAvailableSpaceCommand updateWarehouseAvailableSpaceCommand;

    private FindAllWarehouseQuery findAllWarehouseQuery;

    private FindWarehouseQuery findWarehouseQuery;

    private FindWarehouseQuery findWrongWarehouseQuery;

    private FindWarehouseResponse findWarehouseResponse;

    private FindAllWarehouseResponse findAllWarehouseResponse;

    private DomainPage<Warehouse> warehousePage;

    @BeforeEach
    public void init() {
        warehouse = WarehouseObjectFactory.createWarehouse(AVAILABLE_SPACE);
        createWarehouseCommand = WarehouseObjectFactory.createCreateWarehouseCommand(AVAILABLE_SPACE);

        warehouseUpdatedResponse = WarehouseObjectFactory.createwarehouseUpdatedResponse(warehouse, "", null);

        wrongAvailableSpaceCreateWarehouseCommand = WarehouseObjectFactory.createCreateWarehouseCommand(INVALID_AVAILABLE_SPACE);

        //TODO: this should be instanced from each test to set the specific filters
        findAllWarehouseQuery = WarehouseObjectFactory.createFindAllWarehouseQuery();

        findWarehouseQuery = WarehouseObjectFactory.createFindWarehouseQuery(WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER);

        findWrongWarehouseQuery = WarehouseObjectFactory.createFindWarehouseQuery(WAREHOUSE_WRONG_UNIQUE_PROPERTY_IDENTIFIER);

        approveWarehouseCommand = WarehouseObjectFactory.createApproveWarehouseCommand(WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER);

        approveWrongWarehouseCommand = WarehouseObjectFactory.createApproveWarehouseCommand(WAREHOUSE_WRONG_UNIQUE_PROPERTY_IDENTIFIER);

        warehouseList = WarehouseObjectFactory.createWarehouseList(warehouse);

        cancelWarehouseCommand = WarehouseObjectFactory.createDeleteWarehouseCommand(WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER);

        rejectWarehouseCommand = WarehouseObjectFactory.createRejectWarehouseCommand(WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER);

        updateWarehouseAvailableSpaceCommand = WarehouseObjectFactory.createUpdateWarehouseAvailableSpaceCommand(WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER, AVAILABLE_SPACE);

        findWarehouseResponse = WarehouseObjectFactory.createFindWarehouseResponse(warehouse);

        warehousePage = WarehouseObjectFactory.createWarehouseDomainpage(Collections.singletonList(warehouse), PAGE, SIZE);

        findAllWarehouseResponse = WarehouseObjectFactory.createFindAllWarehouseResponse(warehousePage);


        when(warehouseDomainMapper.createWarehouseCommandToWarehouse(createWarehouseCommand)).thenReturn(warehouse);
        when(warehouseDomainMapper.warehouseToWarehouseUpdatedResponse(warehouse, "ACTUALIZADO")).thenReturn(warehouseUpdatedResponse);
        when(warehouseDomainMapper.warehouseToWarehouseList(warehouse)).thenReturn(warehouseList);
        when(warehouseDomainMapper.warehouseToFindWarehouseResponse(warehouse)).thenReturn(findWarehouseResponse);


        when(warehouseRepository.findAllByParameters(
                findAllWarehouseQuery.getPage(),
                findAllWarehouseQuery.getSize(),
                findAllWarehouseQuery.getFields(),
                findAllWarehouseQuery.getSortingValue(),
                findAllWarehouseQuery.getCountry(),
                findAllWarehouseQuery.getState(),
                findAllWarehouseQuery.getCity()
        ))
                .thenReturn(new DomainPage<>(Collections.singletonList(warehouse), findAllWarehouseQuery.getPage(), findAllWarehouseQuery.getSize(), TOTAL_RESULT));
        when(warehouseDomainMapper.domainPageWarehouseToFindAllWarehouseResponse(warehousePage)).thenReturn(findAllWarehouseResponse);
        when(warehouseRepository.findByWarehouseUniquePropertyIdentifier(WAREHOUSE_UNIQUE_PROPERTY_IDENTIFIER))
                .thenReturn(Optional.of(warehouse));
        when(warehouseRepository.findByWarehouseUniquePropertyIdentifier(WAREHOUSE_WRONG_UNIQUE_PROPERTY_IDENTIFIER))
                .thenReturn(Optional.empty());
        when(warehouseRepository.save(any(Warehouse.class))).thenReturn(warehouse);
        //doNothing().when(warehouseRepository.updateWarehouseStatus(new WarehouseId(WAREHOUSE_ID),WarehouseStatus.CREATED);

    }

    @Test
    void givenARightCreateWarehouseCommand_whenAttemptToCreate_thenTheWarehouseShouldBeCreatedAsPending() {
        warehouseUpdatedResponse = WarehouseObjectFactory.createwarehouseUpdatedResponse(warehouse, WarehouseDomainConstant.WAREHOUSE_CREATION_SUCCESS, WarehouseStatus.PENDING);
        when(warehouseDomainMapper.warehouseToWarehouseUpdatedResponse(warehouse, WarehouseDomainConstant.WAREHOUSE_CREATION_SUCCESS)).thenReturn(warehouseUpdatedResponse);
        WarehouseUpdatedResponse createdWarehouseResponse = warehouseApplicationService.createWarehouse(createWarehouseCommand);
        Assertions.assertEquals(WarehouseStatus.PENDING, createdWarehouseResponse.getWarehouseStatus());
        Assertions.assertEquals(createdWarehouseResponse.getMessage(), WarehouseDomainConstant.WAREHOUSE_CREATION_SUCCESS);
        Assertions.assertNotNull(createdWarehouseResponse.getWarehouseUniquePropertyIdentifier());
    }

    //TODO: this will be tested when other business rules are created
//    @Test
//    void givenARightCreateWarehouseCommand_whenAttemptToApprove_thenShouldThrowAnException(){
//
//    }

    @Test
    void givenAWrongAvailableSpaceCreateWarehouseCommand_whenAttemptToCreate_thenShouldThrowAnException() {
        when(warehouseDomainMapper.createWarehouseCommandToWarehouse(wrongAvailableSpaceCreateWarehouseCommand))
                .thenReturn(WarehouseObjectFactory.createWarehouse(INVALID_AVAILABLE_SPACE));
        WarehouseDomainException warehouseDomainException = Assertions.assertThrows(
                WarehouseDomainException.class,
                () -> warehouseApplicationService.createWarehouse(wrongAvailableSpaceCreateWarehouseCommand)
        );
        Assertions.assertEquals(WarehouseDomainConstant.WAREHOUSE_INVALID_AVAILABLE_SPACE, warehouseDomainException.getMessage());
    }

    @Test
    void givenAFindAllWarehouseQuery_whenAttemptToFindByCriteria_thenShouldReturnAPageOfFindAllWarehouseResponse() {
        List<WarehouseList> warehouseLists = Stream.of(warehouse).map(warehouseDomainMapper::warehouseToWarehouseList).toList();
        FindAllWarehouseResponse findAllWarehouseResponse = warehouseApplicationService.findAllWarehouses(findAllWarehouseQuery);
        Assertions.assertEquals(findAllWarehouseResponse.getWarehouses(), warehouseLists);
        Assertions.assertEquals(findAllWarehouseResponse.getPage(), PAGE);
        Assertions.assertEquals(findAllWarehouseResponse.getSize(), SIZE);
        Assertions.assertEquals(findAllWarehouseResponse.getTotalResult(), TOTAL_RESULT);
    }

    @Test
    void givenAFindWarehouseQuery_whenAttemptToFindByWarehouseUniquePropertyIdentifier_thenShouldReturnFindWarehouseResponse() {
        FindWarehouseResponse findWarehouseResponse = warehouseApplicationService.findWarehouseByWarehouseUniquePropertyIdentifier(findWarehouseQuery);
        Assertions.assertEquals(warehouse.getWarehouseUniquePropertyIdentifier(), findWarehouseResponse.getWarehouseUniquePropertyIdentifier());
    }

    @Test
    void givenAWrongFindWarehouseQuery_whenAttemptToFindByWarehouseUniquePropertyIdentifier_thenShouldThrowNotFoundException() {
        WarehouseNotFoundException warehouseNotFoundException = Assertions.assertThrows(
                WarehouseNotFoundException.class,
                () -> warehouseApplicationService.findWarehouseByWarehouseUniquePropertyIdentifier(findWrongWarehouseQuery)
        );
        Assertions.assertEquals(String.format(WarehouseDomainConstant.WAREHOUSE_NOT_FOUND, WAREHOUSE_WRONG_UNIQUE_PROPERTY_IDENTIFIER), warehouseNotFoundException.getMessage());
    }

    @Test
    void givenARightApproveWarehouseCommand_whenAttemptToApproveWarehouse_thenShouldReturnWarehouseUpdatedResponse() {
        warehouseUpdatedResponse = WarehouseObjectFactory.createwarehouseUpdatedResponse(warehouse, "", WarehouseStatus.CREATED);
        when(warehouseDomainMapper.warehouseToWarehouseUpdatedResponse(warehouse, "ACTUALIZADO")).thenReturn(warehouseUpdatedResponse);
        warehouse.initializeWarehouse();
        WarehouseUpdatedResponse response = warehouseApplicationService.approveWarehouse(approveWarehouseCommand);
        Assertions.assertEquals(warehouse.getWarehouseStatus(), response.getWarehouseStatus());
    }

    //TODO: this test is not necessary
    /*@Test
    void givenAWrongApproveWarehouseCommand_whenAttemptToApproveWarehouse_thenShouldThrowWarehouseNotFoundException(){
        WarehouseNotFoundException warehouseDomainException = Assertions.assertThrows(
                WarehouseNotFoundException.class,
                ()->warehouseApplicationService.approveWarehouse(approveWrongWarehouseCommand)
        );
        Assertions.assertEquals(String.format(WarehouseDomainConstant.WAREHOUSE_NOT_FOUND,WAREHOUSE_WRONG_UNIQUE_PROPERTY_IDENTIFIER),warehouseDomainException.getMessage());
    }*/

    @Test
    void givenAWrongWarehouseStatus_whenAttemptToApproveWarehouse_thenShouldThrowWarehouseDomainException() {
        WarehouseDomainException warehouseDomainException = Assertions.assertThrows(
                WarehouseDomainException.class,
                () -> warehouseApplicationService.approveWarehouse(approveWarehouseCommand)
        );
        Assertions.assertEquals(WarehouseDomainConstant.WAREHOUSE_WRONG_STATE_APPROVAL, warehouseDomainException.getMessage());
    }

    @Test
    void givenAWrongCommand_whenAttemptToFindWarehouse_thenShouldThrowNotFoundException() {
        WarehouseNotFoundException warehouseNotFoundException = Assertions.assertThrows(
                WarehouseNotFoundException.class,
                () -> CommonWarehouseDomain.findWarehouseByWarehouseUniquePropertyIdentifier(
                        warehouseRepository,
                        WAREHOUSE_WRONG_UNIQUE_PROPERTY_IDENTIFIER,
                        LoggerFactory.getLogger(WarehouseApplicationServiceTest.class)
                )
        );
        Assertions.assertEquals(String.format(WarehouseDomainConstant.WAREHOUSE_NOT_FOUND, WAREHOUSE_WRONG_UNIQUE_PROPERTY_IDENTIFIER), warehouseNotFoundException.getMessage());
    }

}