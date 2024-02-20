package com.shopper.autos.system.domain.event.publisher;

import com.shopper.autos.system.domain.event.DomainEvent;

public interface DomainEventPublisher <T extends DomainEvent<?>>{
    void publish(T domainEvent);
}
