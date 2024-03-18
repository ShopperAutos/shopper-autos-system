package com.shopper.autos.system.warehouse.service.infrastructure;

import com.shopper.autos.system.warehouse.service.domain.valueobjects.WarehouseStatus;
import com.shopper.autos.system.warehouse.service.infrastructure.entity.WarehouseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public class WarehouseInfrastructureObjectFactory {

    private static List<String> warehouseUniquePropertyIdentifiers = new ArrayList<>();


    public static List<WarehouseEntity> getWarehouses() {

        List<WarehouseEntity> warehouseEntities = new ArrayList<>();

        IntStream.range(0, 15).forEach(index -> {
            generateRandomWarehouseUniquePropertyIdentifier(warehouseUniquePropertyIdentifiers);
            warehouseEntities.add(WarehouseEntity.builder()
                    .id(UUID.randomUUID())
                    .warehouseUniquePropertyIdentifier(warehouseUniquePropertyIdentifiers.get(index))
                    .address(
                            AddressFactory.createRandomEntity(index)
                    )
                    .maxCapacity(20)
                    .availableSpace(20)
                    .warehouseStatus(WarehouseStatus.PENDING)
                    .build());
        });
        return warehouseEntities;
    }

    private static void generateRandomWarehouseUniquePropertyIdentifier(List<String> warehouseUniquePropertyIdentifier) {
        Random random = new Random();

        while (true) {
            int firstPart = random.nextInt(900) + 100;
            int secondPart = random.nextInt(900) + 100;
            int thirdPart = random.nextInt(900) + 100;

            String randomUniqueId = String.format("%d-%d-%d", firstPart, secondPart, thirdPart);

            if (!warehouseUniquePropertyIdentifier.contains(randomUniqueId)) {
                warehouseUniquePropertyIdentifier.add(randomUniqueId);
                return;
            }
        }
    }

}
