package com.eventsourcecqrs.application.query.writer;

import com.eventsourcecqrs.port.adapter.repository.projections.writer.WriterProjection;

import java.util.Collection;

public interface WriterQuery {
    Collection<WriterProjection> query();
}
