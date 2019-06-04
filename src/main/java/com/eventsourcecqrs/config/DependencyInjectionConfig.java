package com.eventsourcecqrs.config;

import com.eventsourcecqrs.domain.model.base.DomainEventNotifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DependencyInjectionConfig {

    @Bean
    public DomainEventNotifier domainEventNotifier(ApplicationContext applicationContext) {
        DomainEventNotifier domainEventNotifier = new SpringDomainEventNotifier(applicationContext);
        DomainEventNotifier.setCurrentDomainEventNotifier(domainEventNotifier);
        return DomainEventNotifier.currentNotifier();
    }
}
