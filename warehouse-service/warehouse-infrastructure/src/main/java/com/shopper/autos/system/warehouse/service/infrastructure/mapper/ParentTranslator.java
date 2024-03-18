package com.shopper.autos.system.warehouse.service.infrastructure.mapper;

public interface ParentTranslator<Origin, Destination> {
    Destination originToDestination(Origin origin);

    Origin destinationToOrigin(Destination origin);
}
