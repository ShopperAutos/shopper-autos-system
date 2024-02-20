package com.shopper.autos.system.warehouse.service.domain.dto.query;

import com.shopper.autos.system.domain.valueobject.SortingValue;
import com.shopper.autos.system.warehouse.service.domain.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class FindAllWarehouseQuery implements Request<FindAllWarehouseResponse> {

    private final Integer page;
    private final Integer size;
    private final List<String> fields;
    private final SortingValue sortingValue;
    private final String country;
    private final String state;
    private final String city;
    private final String address;

}
