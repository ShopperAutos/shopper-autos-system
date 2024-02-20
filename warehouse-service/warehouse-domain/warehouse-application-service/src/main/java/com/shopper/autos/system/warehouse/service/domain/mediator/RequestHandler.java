package com.shopper.autos.system.warehouse.service.domain.mediator;

public interface RequestHandler <TRequest extends Request<TResponse>, TResponse>{
    TResponse handle(TRequest request);
}
