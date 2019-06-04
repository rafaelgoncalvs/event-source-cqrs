package com.eventsourcecqrs.domain.model.book;

import com.eventsourcecqrs.domain.model.base.DomainEvent;
import com.eventsourcecqrs.domain.model.base.EntityState;
import com.eventsourcecqrs.domain.model.base.UniqueIdentifier;
import com.eventsourcecqrs.domain.model.writer.WriterId;

import java.time.LocalDateTime;
import java.util.List;

public class BookState extends EntityState {

    private BookId id;
    private WriterId writerId;
    private String title;

    BookState(List<DomainEvent> events) {
        super(events);
    }

    private void when(BookCreated event) {
        this.id = event.id();
        this.writerId = event.writerId();
        this.title = event.title();
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public UniqueIdentifier id() {
        return id;
    }

    public WriterId writerId() {
        return writerId;
    }

    public String title() {
        return title;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
