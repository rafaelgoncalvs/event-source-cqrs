package com.eventsourcecqrs.port.adapter.repository.projections.book;

import java.util.Collection;

public interface BookProjectionRepository {
    void add(BookProjection bookProjection);
    Collection<BookProjection> getAll();
}
