package com.eventsourcecqrs.port.adapter.webapi.writer;

import com.eventsourcecqrs.application.command.base.Command;
import com.eventsourcecqrs.application.command.base.ReturnCommandHandler;
import com.eventsourcecqrs.application.command.writer.CreateWriterCommand;
import com.eventsourcecqrs.application.command.writer.WriterCommandHandler;
import com.eventsourcecqrs.application.query.writer.WriterQuery;
import com.eventsourcecqrs.port.adapter.repository.projections.writer.WriterProjection;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "writers", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WriterResource {

    private WriterCommandHandler writerCommandHandler;
    private WriterQuery writerQuery;

    public WriterResource(WriterCommandHandler writerCommandHandler, WriterQuery writerQuery) {
        this.writerCommandHandler = writerCommandHandler;
        this.writerQuery = writerQuery;
    }

    @PostMapping
    public ResponseEntity post(@RequestBody WriterRequest request) {
        Command command = new CreateWriterCommand(request.name);
        ReturnCommandHandler returnCommandHandler = writerCommandHandler.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnCommandHandler.value().value());
    }

    @GetMapping
    public Collection<WriterProjection> getAll() {
        return writerQuery.query();
    }
}
