package com.example.sharedkernel.domain.events.orders;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

@Getter
public class OrderItemRemoved extends DomainEvent {

    private String pizzaId;
    private int quantity;

    public OrderItemRemoved(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
    }

    public OrderItemRemoved(String topic, String pizzaId, int quantity){
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
        this.pizzaId = pizzaId;
        this.quantity = quantity;
    }


}
