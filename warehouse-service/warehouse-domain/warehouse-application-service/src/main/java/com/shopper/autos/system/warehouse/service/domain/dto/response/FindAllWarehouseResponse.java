package com.shopper.autos.system.warehouse.service.domain.dto.response;

import com.shopper.autos.system.warehouse.service.domain.dto.WarehouseList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class FindAllWarehouseResponse {

    private final List<WarehouseList> warehouses;
    private final Integer page;
    private final Integer size;
    private final Long totalResult;
}
