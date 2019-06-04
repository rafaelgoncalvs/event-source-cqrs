package com.eventsourcecqrs.port.adapter.repository.projections.book;

import com.eventsourcecqrs.domain.model.base.DomainEventHandler;
import com.eventsourcecqrs.domain.model.book.BookCreated;
import org.springframework.stereotype.Service;

@Service
public class CreateBookProjectionEventHandler implements DomainEventHandler<BookCreated> {

    private BookProjectionRepository bookProjectionRepository;

    public CreateBookProjectionEventHandler(BookProjectionRepository bookProjectionRepository) {
        this.bookProjectionRepository = bookProjectionRepository;
    }

    @Override
    public void handle(BookCreated event) {
        BookProjection bookProjection = new BookProjection();
        bookProjection.id = event.id().value();
        bookProjection.writerId = event.writerId().value();
        bookProjection.title = event.title();

        bookProjectionRepository.add(bookProjection);
    }
}
