package com.shopper.autos.system.warehouse.service.infrastructure;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.shopper.autos.system.warehouse.service.infrastructure"})
@SpringBootApplication(scanBasePackages = "com.shopper.autos.system")
public class WarehouseInfrastructureTestConfiguration {
}
