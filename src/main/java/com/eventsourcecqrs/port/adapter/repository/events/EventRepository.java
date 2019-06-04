package com.eventsourcecqrs.port.adapter.repository.events;

import com.eventsourcecqrs.domain.model.base.UniqueIdentifier;

import java.util.List;

public interface EventRepository {
    List<DomainEventEntry> get(UniqueIdentifier entityId);
    void add(DomainEventEntry domainEventEntry);
}
