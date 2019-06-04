package com.eventsourcecqrs.domain.model.base;

public interface DomainEventHandler<E extends DomainEvent> {
    void handle(E event);
}
