package com.shopper.autos.system.warehouse.service.domain.dto.query;

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
    private final Integer totalResult;
}
