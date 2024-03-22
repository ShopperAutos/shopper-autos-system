package com.shopper.autos.system.infrastructure.mapper;

public class GenericMapperImpl<Origin, Destination> implements GenericMapper<Origin, Destination> {

    private final ParentTranslator<Origin, Destination> translator;

    public GenericMapperImpl(ParentTranslator<Origin, Destination> translator) {
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
