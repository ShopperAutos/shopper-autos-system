package com.shopper.autos.system.infrastructure.mapper;

public class BaseMapperImpl<Origin, Destination> implements BaseMapper<Origin, Destination> {

    private final ParentTranslator<Origin, Destination> translator;

    public BaseMapperImpl(ParentTranslator<Origin, Destination> translator) {
        this.translator = translator;
    }

    @Override
    public Destination map(Origin origin) {
        return this.translator.originToDestination(origin);
    }

    @Override
    public Origin reverseMap(Destination destination) {
        return this.translator.destinationToOrigin(destination);
    }
}
