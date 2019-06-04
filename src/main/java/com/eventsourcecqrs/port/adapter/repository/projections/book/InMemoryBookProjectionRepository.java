package com.eventsourcecqrs.port.adapter.repository.projections.book;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryBookProjectionRepository implements BookProjectionRepository {

    private Map<UUID, BookProjection> bookProjections = new HashMap<>();
    @Override
    public void add(BookProjection bookProjection) {
        bookProjections.put(bookProjection.id, bookProjection);
    }

    @Override
    public Collection<BookProjection> getAll() {
        return bookProjections.values();
    }
}
