package com.shopper.autos.system.warehouse.service.domain;

import com.shopper.autos.system.warehouse.service.domain.dto.command.ApproveWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.command.CreateWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.command.DeleteWarehouseCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.command.UpdateWarehouseAvailableSpaceCommand;
import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.query.FindAllWarehouseQuery;
import com.shopper.autos.system.warehouse.service.domain.dto.response.FindAllWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.query.FindWarehouseQuery;
import com.shopper.autos.system.warehouse.service.domain.dto.response.FindWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.mediator.Mediator;
import com.shopper.autos.system.warehouse.service.domain.port.input.service.WarehouseApplicationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class WarehouseApplicationServiceImpl implements WarehouseApplicationService {

    private final Mediator mediator;

    WarehouseApplicationServiceImpl(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public WarehouseUpdatedResponse createWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        return mediator.send(createWarehouseCommand);
    }

    @Override
    public FindAllWarehouseResponse findAllWarehouses(FindAllWarehouseQuery findAllWarehouseQuery) {
        return mediator.send(findAllWarehouseQuery);
    }

    @Override
    public FindWarehouseResponse findWarehouseByWarehouseUniquePropertyIdentifier(FindWarehouseQuery findWarehouseQuery) {
        return mediator.send(findWarehouseQuery);
    }

    @Override
    public WarehouseUpdatedResponse updateWarehouseAvailableSpace(UpdateWarehouseAvailableSpaceCommand updateWarehouseAvailableSpace) {
        return mediator.send(updateWarehouseAvailableSpace);
    }

    @Override
    public WarehouseUpdatedResponse deleteWarehouse(DeleteWarehouseCommand deleteWarehouseCommand) {
        return mediator.send(deleteWarehouseCommand);
    }

    @Override
    public WarehouseUpdatedResponse approveWarehouse(ApproveWarehouseCommand approveWarehouseCommand) {
        return mediator.send(approveWarehouseCommand);
    }


}
