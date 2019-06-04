package com.eventsourcecqrs.domain.model.writer;

import com.eventsourcecqrs.domain.model.base.UniqueIdentifier;

import java.util.UUID;

public class WriterId extends UniqueIdentifier {

    public WriterId(UUID value) {
        super(value);
    }
}
