package com.eventsourcecqrs.domain.model.book;

import com.eventsourcecqrs.domain.model.base.UniqueIdentifier;

import java.util.UUID;

public class BookId extends UniqueIdentifier {

    public BookId(UUID value) {
        super(value);
    }
}
