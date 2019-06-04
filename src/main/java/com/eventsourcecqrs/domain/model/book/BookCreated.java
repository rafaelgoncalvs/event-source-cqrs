package com.eventsourcecqrs.domain.model.book;

import com.eventsourcecqrs.domain.model.base.DomainEvent;
import com.eventsourcecqrs.domain.model.writer.WriterId;

public class BookCreated extends DomainEvent {

    private BookId id;
    private WriterId writerId;
    private String title;

    public BookCreated(BookId id, WriterId writerId, String title) {
        this.id = id;
        this.writerId = writerId;
        this.title = title;
    }

    public BookId id() {
        return id;
    }

    public WriterId writerId() {
        return writerId;
    }

    public String title() {
        return title;
    }

    public BookId getId() {
        return id;
    }

    public void setId(BookId id) {
        this.id = id;
    }

    public WriterId getWriterId() {
        return writerId;
    }

    public void setWriterId(WriterId writerId) {
        this.writerId = writerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
