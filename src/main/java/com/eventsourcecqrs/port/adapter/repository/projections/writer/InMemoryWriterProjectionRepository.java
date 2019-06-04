package com.eventsourcecqrs.port.adapter.repository.projections.writer;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class InMemoryWriterProjectionRepository implements WriterProjectionRepository {

    private Map<UUID, WriterProjection> writerProjections = new HashMap<>();

    @Override
    public void add(WriterProjection writerProjection) {
        writerProjections.put(writerProjection.id, writerProjection);
    }

    @Override
    public Collection<WriterProjection> getAll() {
        return writerProjections.values();
    }
}
