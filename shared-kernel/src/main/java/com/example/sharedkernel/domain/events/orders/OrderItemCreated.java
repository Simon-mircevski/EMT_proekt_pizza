package com.example.sharedkernel.domain.events.orders;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

@Getter
public class OrderItemCreated extends DomainEvent {

    private String pizzaId;
    private int quantity;

    public OrderItemCreated(String pizzaId, int quantity) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
        this.pizzaId = pizzaId;
        this.quantity = quantity;
    }
}
