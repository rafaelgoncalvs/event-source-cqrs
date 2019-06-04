package com.eventsourcecqrs.port.adapter.repository.projections.writer;

import com.eventsourcecqrs.application.query.writer.WriterQuery;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class WriterQueryImpl implements WriterQuery {

    private final WriterProjectionRepository writerProjectionRepository;

    public WriterQueryImpl(WriterProjectionRepository writerProjectionRepository) {
        this.writerProjectionRepository = writerProjectionRepository;
    }

    @Override
    public Collection<WriterProjection> query() {
        return writerProjectionRepository.getAll();
    }
}
