package com.eventsourcecqrs.domain.model.book;

import com.eventsourcecqrs.domain.model.base.DomainEvent;
import com.eventsourcecqrs.domain.model.base.Entity;
import com.eventsourcecqrs.domain.model.writer.WriterId;

import java.util.List;

public class Book extends Entity<BookId, BookState> {

    private BookState currentState;

    public Book(List<DomainEvent> events) {
        this.currentState = new BookState(events);
    }

    public void create(BookId id, WriterId writerId, String title) {
        BookCreated bookCreated = new BookCreated(id, writerId, title);
        applyEvent(bookCreated);
    }

    @Override
    public BookState currentState() {
        return currentState;
    }
}
