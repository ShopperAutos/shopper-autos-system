package com.shopper.autos.system.warehouse.service.infrastructure;

public interface GenericInfrastructureMapper {
    <TOrigin, TDestination> TDestination map(TOrigin entity);
}
