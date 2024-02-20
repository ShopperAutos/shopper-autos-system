package com.shopper.autos.system.warehouse.service.domain.port.output.publisher;

import com.shopper.autos.system.domain.event.publisher.DomainEventPublisher;
import com.shopper.autos.system.warehouse.service.domain.event.WarehouseCreatedEvent;

public interface WarehouseCreatedMessagePublisher extends DomainEventPublisher<WarehouseCreatedEvent> {
}
