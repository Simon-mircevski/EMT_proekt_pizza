package com.example.sharedkernel.domain.infra;

import com.example.sharedkernel.domain.events.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
