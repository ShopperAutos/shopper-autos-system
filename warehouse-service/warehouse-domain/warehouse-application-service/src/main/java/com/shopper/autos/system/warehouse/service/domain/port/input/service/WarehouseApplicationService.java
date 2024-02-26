package com.shopper.autos.system.warehouse.service.domain.port.input.service;

import com.shopper.autos.system.warehouse.service.domain.dto.command.*;
import com.shopper.autos.system.warehouse.service.domain.dto.response.WarehouseUpdatedResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.query.FindAllWarehouseQuery;
import com.shopper.autos.system.warehouse.service.domain.dto.response.FindAllWarehouseResponse;
import com.shopper.autos.system.warehouse.service.domain.dto.query.FindWarehouseQuery;
import com.shopper.autos.system.warehouse.service.domain.dto.response.FindWarehouseResponse;

public interface WarehouseApplicationService {

    WarehouseUpdatedResponse createWarehouse(CreateWarehouseCommand createWarehouseCommand);
    FindAllWarehouseResponse findAllWarehouses(FindAllWarehouseQuery findAllWarehouseQuery);
    FindWarehouseResponse findWarehouseByWarehouseUniquePropertyIdentifier(FindWarehouseQuery findWarehouseQuery);
    WarehouseUpdatedResponse updateWarehouseAvailableSpace(UpdateWarehouseAvailableSpaceCommand updateWarehouseAvailableSpace);
    WarehouseUpdatedResponse deleteWarehouse(DeleteWarehouseCommand deleteWarehouseCommand);
    WarehouseUpdatedResponse approveWarehouse(ApproveWarehouseCommand approveWarehouseCommand);
    WarehouseUpdatedResponse rejectWarehouse(RejectWarehouseCommand rejectWarehouseCommand);

}
