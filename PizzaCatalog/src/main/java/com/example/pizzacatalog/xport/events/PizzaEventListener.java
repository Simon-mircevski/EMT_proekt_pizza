package com.example.pizzacatalog.xport.events;

import com.example.pizzacatalog.domain.models.PizzaId;
import com.example.pizzacatalog.services.PizzaService;
import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.domain.events.orders.OrderItemCreated;
import com.example.sharedkernel.domain.events.orders.OrderItemRemoved;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PizzaEventListener {

    private final PizzaService pizzaService;

    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_CREATED, groupId = "pizzaCatalog")
    public void consumeOrderItemCreatedEvent(String jsonMessage){
        try{
            OrderItemCreated event = DomainEvent.fromJson(jsonMessage, OrderItemCreated.class);
            pizzaService.orderItemCreated(PizzaId.of(event.getPizzaId()), event.getQuantity());
        }
        catch (Exception e){

        }
    }

    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_REMOVED, groupId = "pizzaCatalog")
    public void consumeOrderItemRemovedEvent(String jsonMessage){
        try{
            OrderItemRemoved event = DomainEvent.fromJson(jsonMessage, OrderItemRemoved.class);
            pizzaService.orderItemRemoved(PizzaId.of(event.getPizzaId()), event.getQuantity());
        }
        catch (Exception e){

        }
    }
}
