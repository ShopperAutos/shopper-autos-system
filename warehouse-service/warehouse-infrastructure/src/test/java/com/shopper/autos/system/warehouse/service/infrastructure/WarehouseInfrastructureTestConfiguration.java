package com.shopper.autos.system.warehouse.service.infrastructure;

import com.shopper.autos.system.infrastructure.mapper.GenericMapper;
import com.shopper.autos.system.infrastructure.mapper.GenericMapperImpl;
import com.shopper.autos.system.warehouse.service.domain.entity.Warehouse;
import com.shopper.autos.system.warehouse.service.domain.port.output.repository.WarehouseRepository;
import com.shopper.autos.system.warehouse.service.infrastructure.adapter.WarehouseRepositoryAdapter;
import com.shopper.autos.system.warehouse.service.infrastructure.entity.WarehouseEntity;
import com.shopper.autos.system.warehouse.service.infrastructure.repository.GenericJpaRepository;
import com.shopper.autos.system.warehouse.service.infrastructure.repository.WarehouseJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@EnableJpaRepositories(basePackages = {"com.shopper.autos.system.warehouse.service.infrastructure"})
@SpringBootApplication(scanBasePackages = "com.shopper.autos.system")
public class WarehouseInfrastructureTestConfiguration {

    @Autowired
    private GenericJpaRepository<WarehouseEntity, UUID> genericJpaRepository;

    @Autowired
    private WarehouseJpaRepository warehouseJpaRepository;

    @Bean
    public WarehouseMapstruct warehouseMapstruct(){
        return WarehouseMapstruct.INSTANCE;
    }

    @Bean
    public GenericMapper<Warehouse, WarehouseEntity> genericMapper() {
        return new GenericMapperImpl<>(WarehouseMapstruct.INSTANCE);
    }

    @Bean
    public WarehouseRepository warehouseRepository() {
        return new WarehouseRepositoryAdapter(genericMapper(), warehouseJpaRepository);
    }

    /*@Autowired
    private WarehouseJpaRepository warehouseJpaRepository;
    @Autowired
    private GenericJpaRepository<WarehouseEntity, UUID> genericJpaRepository;

    @Bean
    public GenericMapper<Warehouse, WarehouseEntity> genericMapper() {
        return new GenericMapperImpl<>(WarehouseMapstruct.INSTANCE);
    }

    @Bean
    public WarehouseRepository warehouseRepository() {
        return new WarehouseRepositoryAdapter(genericMapper(), genericJpaRepository, warehouseJpaRepository);
    }*/

}
