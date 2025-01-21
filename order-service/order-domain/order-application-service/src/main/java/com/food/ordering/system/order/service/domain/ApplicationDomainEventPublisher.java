package com.food.ordering.system.order.service.domain;

import food.ordering.system.domain.event.publisher.DomainEventPublisher;
import food.ordering.system.order.service.domain.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationDomainEventPublisher implements ApplicationEventPublisherAware, DomainEventPublisher<OrderCreatedEvent> {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(OrderCreatedEvent orderCreatedEvent) {
        this.applicationEventPublisher.publishEvent(orderCreatedEvent);
        log.info("OrderCreatedEvent is published for order with id: {}", orderCreatedEvent.getOrder().getId().getValue());
    }
}
