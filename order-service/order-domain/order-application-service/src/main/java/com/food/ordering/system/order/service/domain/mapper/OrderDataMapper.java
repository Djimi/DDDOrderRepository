package com.food.ordering.system.order.service.domain.mapper;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import food.ordering.system.domain.valueobject.CustomerId;
import food.ordering.system.domain.valueobject.Money;
import food.ordering.system.domain.valueobject.ProductId;
import food.ordering.system.domain.valueobject.RestaurantId;
import food.ordering.system.order.service.domain.entity.Order;
import food.ordering.system.order.service.domain.entity.OrderItem;
import food.ordering.system.order.service.domain.entity.Product;
import food.ordering.system.order.service.domain.entity.Restaurant;
import food.ordering.system.order.service.domain.valueobject.StreetAddress;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {

    public TrackOrderResponse orderToTrackOrderResponse(Order order) {
        return TrackOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .failureMessages(order.getFailureMessages())
                .build();
    }
    public CreateOrderResponse orderToCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .build();
    }
    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return Restaurant.Builder.newBuilder().restaurantId(new RestaurantId(createOrderCommand.getRestaurantId())).products(
                createOrderCommand.getItems().stream().map(orderItem -> {
                    return new Product(new ProductId(orderItem.getProductId()));
                }).collect(Collectors.toList())).build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.Builder.builder().customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemToOrderItemEntities(createOrderCommand.getItems()))
                .build();
    }

    private List<OrderItem> orderItemToOrderItemEntities(@NotNull List<com.food.ordering.system.order.service.domain.dto.create.OrderItem> items) {
        return items.stream().map(orderItem ->
            OrderItem.Builder.builder()
                    .product(new Product(new ProductId(orderItem.getProductId())))
                    .price(new Money(orderItem.getPrice()))
                    .quantity(orderItem.getQuantity())
                    .subTotal(new Money(orderItem.getSubTotal()))
                    .build())
                .collect(Collectors.toList());
    }

    private StreetAddress orderAddressToStreetAddress(@NotNull OrderAddress address) {
        return new StreetAddress(UUID.randomUUID(), address.getStreet(), address.getPostalCode(), address.getCity());
    }
}
