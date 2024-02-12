package com.shopper.autos.system.warehouse.service.domain.exception;

public class WarehouseNotFoundException extends WarehouseDomainException{
    public WarehouseNotFoundException(String message) {
        super(message);
    }

    public WarehouseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
