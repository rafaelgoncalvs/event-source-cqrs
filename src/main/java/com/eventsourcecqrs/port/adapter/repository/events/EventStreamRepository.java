package com.eventsourcecqrs.port.adapter.repository.events;

import com.eventsourcecqrs.domain.model.base.*;
import com.google.gson.Gson;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class EventStreamRepository implements EventStore {

    private final EventRepository eventRepository;
    private final Gson gson;

    public EventStreamRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
        gson = new Gson();
    }

    @Override
    public EventStream get(UniqueIdentifier id) {
        List<DomainEventEntry> domainEventEntries = eventRepository.get(id);
        List<DomainEvent> events = domainEventEntries.stream().map(this::deserializeEvent).collect(Collectors.toList());
        int currentVersion = domainEventEntries.stream().map(DomainEventEntry::getVersion).max(Comparator.comparing(version -> version)).orElse(0);
        return new EventStream(events, currentVersion);
    }

    @Override
    public void add(Entity entity, int currentVersion, List<DomainEvent> events) {
        AtomicInteger version = new AtomicInteger(currentVersion + 1);
        events.forEach(event -> eventRepository.add(new DomainEventEntry(
                entity.id(),
                serializeEvent(event),
                version.getAndIncrement(),
                entity.getClass().getName(),
                event.getClass().getName())));
    }

    private String serializeEvent(DomainEvent event) {
        return gson.toJson(event);
    }

    private DomainEvent deserializeEvent(DomainEventEntry domainEventEntry) {
        try {
            Class<DomainEvent> eventClass = (Class<DomainEvent>) Class.forName(domainEventEntry.getEvent());
            return gson.fromJson(domainEventEntry.getContent(), eventClass);
        } catch (ClassNotFoundException e) {
            throw new EventStoreException(e);
        }
    }
}
