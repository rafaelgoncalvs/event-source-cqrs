package com.eventsourcecqrs.port.adapter.repository.events;

public class EventStoreException extends RuntimeException {
    public EventStoreException(Exception e) {
        super(e);
    }
}
