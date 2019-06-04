package com.eventsourcecqrs.application.command.writer;

import com.eventsourcecqrs.application.command.base.CommandHandler;
import com.eventsourcecqrs.application.command.base.ReturnCommandHandler;
import com.eventsourcecqrs.domain.model.base.EventStore;
import com.eventsourcecqrs.domain.model.writer.Writer;
import com.eventsourcecqrs.domain.model.writer.WriterId;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Consumer;

@Service
public class WriterCommandHandler extends CommandHandler<Writer> {

    public WriterCommandHandler(EventStore eventStore) {
        super(eventStore);
    }

    private ReturnCommandHandler handle(CreateWriterCommand command) {
        WriterId writerId = new WriterId(UUID.randomUUID());
        Consumer<Writer> consumer = (writer) -> writer.create(writerId, command.name());

        applyToEntity(writerId, consumer);

        return ReturnCommandHandler.of(writerId);
    }
}
