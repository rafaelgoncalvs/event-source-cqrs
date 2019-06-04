package com.eventsourcecqrs.port.adapter.repository.projections.writer;

import com.eventsourcecqrs.domain.model.base.DomainEventHandler;
import com.eventsourcecqrs.domain.model.writer.WriterCreated;
import org.springframework.stereotype.Service;

@Service
public class CreateWriterProjectionEventHandler implements DomainEventHandler<WriterCreated> {

    private WriterProjectionRepository writerProjectionRepository;

    public CreateWriterProjectionEventHandler(WriterProjectionRepository writerProjectionRepository) {
        this.writerProjectionRepository = writerProjectionRepository;
    }

    @Override
    public void handle(WriterCreated event) {
        WriterProjection writerProjection = new WriterProjection();
        writerProjection.id = event.id().value();
        writerProjection.name = event.name();

        writerProjectionRepository.add(writerProjection);
    }
}
