package com.eventsourcecqrs.port.adapter.repository.events;

import com.eventsourcecqrs.domain.model.base.UniqueIdentifier;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryEventRepository implements EventRepository {

    private Map<UniqueIdentifier, List<DomainEventEntry>> domainEventEntries = new HashMap<>();

    @Override
    public List<DomainEventEntry> get(UniqueIdentifier entityId) {
        List<DomainEventEntry> domainEventEntries = this.domainEventEntries.get(entityId);
        if(domainEventEntries == null) {
            domainEventEntries = new ArrayList<>();
        }
        return domainEventEntries;
    }

    @Override
    public void add(DomainEventEntry domainEventEntry) {
        List<DomainEventEntry> events = this.domainEventEntries.get(domainEventEntry.getEntityId());
        if(events == null) {
            events = Collections.singletonList(domainEventEntry);
        }
        this.domainEventEntries.put(domainEventEntry.getEntityId(), events);
    }
}
