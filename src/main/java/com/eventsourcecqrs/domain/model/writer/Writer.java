package com.eventsourcecqrs.domain.model.writer;

import com.eventsourcecqrs.domain.model.base.DomainEvent;
import com.eventsourcecqrs.domain.model.base.Entity;

import java.util.List;

public class Writer extends Entity<WriterId, WriterState> {

    private WriterState currentState;

    public Writer(List<DomainEvent> events) {
        this.currentState = new WriterState(events);
    }

    public void create(WriterId id, String name) {
        WriterCreated writerCreated = new WriterCreated(id, name);
        applyEvent(writerCreated);
    }

    @Override
    public WriterState currentState() {
        return currentState;
    }
}
