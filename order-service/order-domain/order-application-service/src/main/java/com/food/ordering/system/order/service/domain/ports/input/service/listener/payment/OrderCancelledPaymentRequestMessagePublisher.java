package com.food.ordering.system.order.service.domain.ports.input.service.listener.payment;

import food.ordering.system.domain.event.publisher.DomainEventPublisher;
import food.ordering.system.order.service.domain.events.OrderCancelledEvent;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {}
