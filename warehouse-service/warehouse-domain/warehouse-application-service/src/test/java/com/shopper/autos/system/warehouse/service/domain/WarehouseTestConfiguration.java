package com.shopper.autos.system.warehouse.service.domain;

import com.shopper.autos.system.warehouse.service.domain.dto.command.ApproveWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.command.CreateWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.query.FindAllWarehouseQuery;
import com.shopper.autos.system.warehouse.service.domain.dto.query.FindWarehouseQuery;
import com.shopper.autos.system.warehouse.service.domain.handler.command.ApproveWarehouseHandler;
import com.shopper.autos.system.warehouse.service.domain.handler.command.CreateWarehouseHandler;
import com.shopper.autos.system.warehouse.service.domain.handler.query.FindAllWarehouseHandler;
import com.shopper.autos.system.warehouse.service.domain.handler.query.FindWarehouseHandler;
import com.shopper.autos.system.warehouse.service.domain.mapper.WarehouseDomainMapper;
import com.shopper.autos.system.warehouse.service.domain.mediator.Mediator;
import com.shopper.autos.system.warehouse.service.domain.mediator.MediatorImpl;
import com.shopper.autos.system.warehouse.service.domain.mediator.RequestHandler;
import com.shopper.autos.system.warehouse.service.domain.port.input.service.WarehouseApplicationService;
import com.shopper.autos.system.warehouse.service.domain.port.output.publisher.WarehouseCreatedMessagePublisher;
import com.shopper.autos.system.warehouse.service.domain.port.output.repository.WarehouseRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(scanBasePackages = "com.shopper.autos.system")
public class WarehouseTestConfiguration {

    @Bean
    public WarehouseRepository warehouseRepository() {
        return Mockito.mock(WarehouseRepository.class);
    }

    @Bean
    public WarehouseDomainService warehouseDomainService() {
        return new WarehouseDomainServiceImpl();
    }

    @Bean
    public WarehouseDomainMapper warehouseDomainMapper() {
        return Mockito.mock(WarehouseDomainMapper.class);
    }

    @Bean
    WarehouseCreatedMessagePublisher warehouseCreatedMessagePublisher() {
        return Mockito.mock(WarehouseCreatedMessagePublisher.class);
    }

    @Bean
    public CreateWarehouseHandler createWarehouseHandler() {
        return new CreateWarehouseHandler(warehouseRepository(), warehouseDomainService(), warehouseDomainMapper(), warehouseCreatedMessagePublisher());
    }

    @Bean
    public FindAllWarehouseHandler findAllWarehouseHandler() {
        return new FindAllWarehouseHandler(warehouseRepository(), warehouseDomainMapper());
    }

    @Bean
    public FindWarehouseHandler findWarehouseHandler() {
        return new FindWarehouseHandler(warehouseRepository(), warehouseDomainMapper());
    }

    @Bean
    public ApproveWarehouseHandler approveWarehouseHandler() {
        return new ApproveWarehouseHandler(warehouseDomainService(), warehouseRepository(), warehouseDomainMapper());
    }

    @Bean
    public Mediator mediator() {
        return new MediatorImpl(getHandlers());
    }

    @Bean
    public WarehouseApplicationService warehouseApplicationService() {
        return new WarehouseApplicationServiceImpl(mediator());
    }

    private Map<Class<?>, RequestHandler<?, ?>> getHandlers() {
        HashMap<Class<?>, RequestHandler<?, ?>> handlers = new HashMap<>();
        handlers.put(CreateWarehouseCommand.class, createWarehouseHandler());
        handlers.put(FindAllWarehouseQuery.class, findAllWarehouseHandler());
        handlers.put(FindWarehouseQuery.class, findWarehouseHandler());
        handlers.put(ApproveWarehouseCommand.class, approveWarehouseHandler());
        return handlers;
    }

}
