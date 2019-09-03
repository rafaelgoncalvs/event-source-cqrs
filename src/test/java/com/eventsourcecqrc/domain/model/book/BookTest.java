package com.eventsourcecqrc.domain.model.book;

import com.eventsourcecqrs.domain.model.base.DomainEvent;
import com.eventsourcecqrs.domain.model.book.Book;
import com.eventsourcecqrs.domain.model.book.BookCreated;
import com.eventsourcecqrs.domain.model.book.BookId;
import com.eventsourcecqrs.domain.model.writer.WriterId;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BookTest {

    @Test
    public void shouldCreateABook() {
        BookId bookId = new BookId(UUID.randomUUID());
        WriterId writerId = new WriterId(UUID.randomUUID());
        String title = "My Book";
        DomainEvent bookCreated = new BookCreated(bookId, writerId, title);
        List<DomainEvent> events = Arrays.asList(bookCreated);

        Book book = new Book(events);

        assertThat(book.id(), is(bookId));
        assertThat(book.currentState().writerId(), is(writerId));
        assertThat(book.currentState().title(), is(title));
    }
}
