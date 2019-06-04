package com.eventsourcecqrs.application.query.book;

import com.eventsourcecqrs.port.adapter.repository.projections.book.BookProjection;

import java.util.Collection;

public interface BookQuery {
    Collection<BookProjection> query();
}
