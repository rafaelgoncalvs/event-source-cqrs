package com.eventsourcecqrs.domain.model.writer;

import com.eventsourcecqrs.domain.model.base.DomainEvent;
import com.eventsourcecqrs.domain.model.base.EntityState;
import com.eventsourcecqrs.domain.model.base.UniqueIdentifier;

import java.time.LocalDateTime;
import java.util.List;

public class WriterState extends EntityState {

    private WriterId id;
    private String name;

    WriterState(List<DomainEvent> events) {
        super(events);
    }

    private void when(WriterCreated event) {
        this.id = event.id();
        this.name = event.name();
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public UniqueIdentifier id() {
        return id;
    }

    public String name() {
        return name;
    }
}
