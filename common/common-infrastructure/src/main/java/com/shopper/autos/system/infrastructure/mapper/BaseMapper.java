package com.shopper.autos.system.infrastructure.mapper;

public interface BaseMapper<Origin, Destination> {

    Destination map(Origin origin);
    Origin reverseMap(Destination destination);

}
