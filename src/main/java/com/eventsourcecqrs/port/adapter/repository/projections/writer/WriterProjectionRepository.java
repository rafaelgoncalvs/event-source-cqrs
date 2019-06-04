package com.eventsourcecqrs.port.adapter.repository.projections.writer;

import java.util.Collection;

public interface WriterProjectionRepository {
    void add(WriterProjection writerbookProjection);
    Collection<WriterProjection> getAll();
}
