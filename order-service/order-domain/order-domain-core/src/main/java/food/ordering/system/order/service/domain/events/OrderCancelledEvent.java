package food.ordering.system.order.service.domain.events;

import food.ordering.system.domain.event.DomainEvent;
import food.ordering.system.order.service.domain.entity.Order;
import food.ordering.system.order.service.domain.entity.OrderItem;

import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent {
    public OrderCancelledEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
