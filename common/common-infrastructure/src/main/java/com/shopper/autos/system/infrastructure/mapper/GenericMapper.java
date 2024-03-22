package com.shopper.autos.system.infrastructure.mapper;

public interface GenericMapper<Origin, Destination> {

    Destination map(Origin origin);
    Origin reverseMap(Destination destination);

}
