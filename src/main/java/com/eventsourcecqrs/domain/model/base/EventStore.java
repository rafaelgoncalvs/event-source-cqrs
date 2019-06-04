package com.eventsourcecqrs.domain.model.base;

import java.util.List;

public interface EventStore {
    EventStream get(UniqueIdentifier id);
    void add(Entity entity, int actualVersion, List<DomainEvent> events);
}
