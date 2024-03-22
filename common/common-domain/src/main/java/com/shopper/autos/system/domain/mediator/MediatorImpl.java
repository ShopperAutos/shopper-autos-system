package com.shopper.autos.system.domain.mediator;

import java.util.Map;

public class MediatorImpl implements Mediator{

    private final Map<Class<?>, RequestHandler<?, ?>> handlers;

    public MediatorImpl(Map<Class<?>, RequestHandler<?, ?>> handlers) {
        this.handlers = handlers;
    }


    @SuppressWarnings("unchecked")
    @Override
    public <TRequest extends Request<TResponse>, TResponse> TResponse send(TRequest request) {
        RequestHandler<TRequest, TResponse> handler = (RequestHandler<TRequest, TResponse>) handlers.get(request.getClass());
        if (handler == null) {
            throw new UnsupportedOperationException("Handler was not found for " + request.getClass().getName());
        }

        return handler.handle(request);
    }
}
