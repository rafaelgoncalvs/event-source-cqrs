package com.eventsourcecqrs.port.adapter.webapi.book;

import com.eventsourcecqrs.application.command.base.Command;
import com.eventsourcecqrs.application.command.base.ReturnCommandHandler;
import com.eventsourcecqrs.application.command.book.BookCommandHandler;
import com.eventsourcecqrs.application.command.book.CreateBookCommand;
import com.eventsourcecqrs.application.query.book.BookQuery;
import com.eventsourcecqrs.domain.model.writer.WriterId;
import com.eventsourcecqrs.port.adapter.repository.projections.book.BookProjection;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "books")
public class BookResource {

    private BookCommandHandler bookCommandHandler;
    private BookQuery bookQuery;

    public BookResource(BookCommandHandler bookCommandHandler, BookQuery bookQuery) {
        this.bookCommandHandler = bookCommandHandler;
        this.bookQuery = bookQuery;
    }

    @PostMapping
    public ResponseEntity post(@RequestBody BookRequest request) {
        Command command = new CreateBookCommand(new WriterId(UUID.fromString(request.writerId)), request.title);
        ReturnCommandHandler returnCommandHandler = bookCommandHandler.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnCommandHandler.value().value());
    }

    @GetMapping
    public Collection<BookProjection> getAll() {
        return bookQuery.query();
    }
}
