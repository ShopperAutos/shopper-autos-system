package com.shopper.autos.system.warehouse.service.domain.handler;

import com.shopper.autos.system.warehouse.service.domain.WarehouseTestConfiguration;
import com.shopper.autos.system.warehouse.service.domain.port.input.service.WarehouseApplicationService;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = WarehouseTestConfiguration.class)
public class ApproveWarehouseTest {

    private WarehouseApplicationService warehouseApplicationService;


}
