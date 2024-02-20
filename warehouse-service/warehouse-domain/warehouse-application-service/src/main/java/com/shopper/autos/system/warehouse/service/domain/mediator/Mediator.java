package com.shopper.autos.system.warehouse.service.domain.mediator;

public interface Mediator {
    <TRequest extends Request<TResponse>,TResponse> TResponse send(TRequest request);
}
