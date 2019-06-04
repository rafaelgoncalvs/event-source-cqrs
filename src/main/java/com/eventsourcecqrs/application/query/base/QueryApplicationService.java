package com.eventsourcecqrs.application.query.base;

public interface QueryApplicationService<R extends QueryReturn> {
    R query(QuerySpecification specification);
}
