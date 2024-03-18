package com.shopper.autos.system.warehouse.service.infrastructure.mapper;

public interface GenericMapper<Origin, Destination> {

    Destination map(Origin origin);
    Origin reverseMap(Destination destination);

}
