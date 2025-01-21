package com.food.ordering.system.order.service.domain.ports.input.service.listener.restaurantapproval;

import food.ordering.system.domain.event.publisher.DomainEventPublisher;
import food.ordering.system.order.service.domain.events.OrderPaidEvent;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {

}
