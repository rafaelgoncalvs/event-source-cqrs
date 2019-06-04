package com.eventsourcecqrs.domain.model.base;

import java.util.Collections;
import java.util.List;

public class EventStream {

    private List<DomainEvent> events;
    private int currentVersion;

    public EventStream(List<DomainEvent> events, int currentVersion) {
        this.events = events;
        this.currentVersion = currentVersion;
    }

    public int currentVersion() {
        return currentVersion;
    }

    public List<DomainEvent> events() {
        return Collections.unmodifiableList(events);
    }

    public DomainEvent lastEvent() {
        return events.get(events.size() - 1);
    }
}
