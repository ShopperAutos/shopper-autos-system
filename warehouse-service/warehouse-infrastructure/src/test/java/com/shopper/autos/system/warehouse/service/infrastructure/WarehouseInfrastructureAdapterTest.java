package com.shopper.autos.system.warehouse.service.infrastructure;

import com.shopper.autos.system.domain.valueobject.Coordinate;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.port.output.repository.WarehouseRepository;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseAddress;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseId;
import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseStatus;
import com.shopper.autos.system.warehouse.service.infrastructure.entity.WarehouseEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
/*@SpringBootTest(classes = WarehouseInfrastructureTestConfiguration.class)*/
@DataJpaTest
public class WarehouseInfrastructureAdapterTest {

    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private WarehouseMapstruct warehouseMapstruct;

    private Warehouse warehouse;

    @BeforeAll
    public void init() {
        warehouse = Warehouse.builder()
                .id(new WarehouseId(UUID.randomUUID()))
                .warehouseUniquePropertyIdentifier("000-000-000")
                .maxCapacity(15)
                .availableSpace(15)
                .warehouseStatus(WarehouseStatus.PENDING)
                .address(new WarehouseAddress(
                        UUID.randomUUID(),
                        "Colombia",
                        "Antioquia",
                        "Rionegro",
                        "A",
                        "Add",
                        "050",
                        new Coordinate(20.0, 20.0)
                ))
                .build();
        WarehouseEntity warehouseEntity = warehouseMapstruct.originToDestination(warehouse);
        Warehouse save = warehouseRepository.save(warehouse);
        System.out.println(save.getAvailableSpace());
    }

    @Test
    void a(){
        System.out.println("a");
    }

}
