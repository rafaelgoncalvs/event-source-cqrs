package com.eventsourcecqrs.domain.model.writer;

import com.eventsourcecqrs.domain.model.base.DomainEvent;

public class WriterCreated extends DomainEvent {

    private WriterId id;
    private String name;

    WriterCreated(WriterId id, String name) {
        this.id = id;
        this.name = name;
    }

    public WriterId id() {
        return id;
    }

    public String name() {
        return name;
    }
}
