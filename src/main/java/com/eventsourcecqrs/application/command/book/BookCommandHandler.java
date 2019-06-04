package com.eventsourcecqrs.application.command.book;

import com.eventsourcecqrs.application.command.base.CommandHandler;
import com.eventsourcecqrs.application.command.base.ReturnCommandHandler;
import com.eventsourcecqrs.domain.model.base.EventStore;
import com.eventsourcecqrs.domain.model.base.EventStream;
import com.eventsourcecqrs.domain.model.book.Book;
import com.eventsourcecqrs.domain.model.book.BookId;
import com.eventsourcecqrs.domain.model.writer.Writer;
import com.eventsourcecqrs.domain.model.writer.WriterId;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Consumer;

@Service
public class BookCommandHandler extends CommandHandler<Book> {

    public BookCommandHandler(EventStore eventStore) {
        super(eventStore);
    }

    private ReturnCommandHandler handle(CreateBookCommand command) {
        BookId bookId = new BookId(UUID.randomUUID());
        Writer writer = getWriter(command.writerId());
        Consumer<Book> consumer = (book) -> book.create(bookId, writer.id(), command.bookTitle());

        applyToEntity(bookId, consumer);

        return ReturnCommandHandler.of(bookId);
    }

    private Writer getWriter(WriterId writerId) {
        EventStream eventStream = eventStore.get(writerId);
        return new Writer(eventStream.events());
    }
}
