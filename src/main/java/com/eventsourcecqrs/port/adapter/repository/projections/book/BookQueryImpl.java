package com.eventsourcecqrs.port.adapter.repository.projections.book;

import com.eventsourcecqrs.application.query.book.BookQuery;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class BookQueryImpl implements BookQuery {

    private BookProjectionRepository bookProjectionRepository;

    public BookQueryImpl(BookProjectionRepository bookProjectionRepository) {
        this.bookProjectionRepository = bookProjectionRepository;
    }

    @Override
    public Collection<BookProjection> query() {
        return bookProjectionRepository.getAll();
    }
}
