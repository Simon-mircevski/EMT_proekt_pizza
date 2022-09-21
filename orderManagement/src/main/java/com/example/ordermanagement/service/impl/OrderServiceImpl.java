package com.example.ordermanagement.service.impl;

import com.example.ordermanagement.domain.exceptions.OrderIdNotExistsException;
import com.example.ordermanagement.domain.exceptions.OrderItemIdNotExistsException;
import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.OrderId;
import com.example.ordermanagement.domain.model.OrderItemId;
import com.example.ordermanagement.domain.repository.OrderRepository;
import com.example.ordermanagement.service.OrderService;
import com.example.ordermanagement.service.forms.OrderForm;
import com.example.ordermanagement.service.forms.OrderItemForm;
import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.domain.events.orders.OrderItemCreated;
import com.example.sharedkernel.domain.events.orders.OrderItemRemoved;
import com.example.sharedkernel.domain.infra.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final Validator validator;

    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm,"order must not be null.");
        var constraintViolations = validator.validate(orderForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        newOrder.getOrderItemList().forEach(item->domainEventPublisher.publish(new OrderItemCreated(item.getPizzaId().getId(),item.getQuantity())));
        return newOrder.getId();

    }

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderRepository.findById(id);
    }

    @Override
    public void addItem(OrderId id, OrderItemForm orderItemForm) throws OrderIdNotExistsException {
        Order order = orderRepository.findById(id).orElseThrow(OrderIdNotExistsException::new);
        order.addItem(orderItemForm.getPizza(), orderItemForm.getQuantity());
        orderRepository.saveAndFlush(order);
        domainEventPublisher.publish(new OrderItemCreated(orderItemForm.getPizza().getId().getId(), orderItemForm.getQuantity()));
    }

    @Override
    public void deleteItem(OrderId id, OrderItemId orderItemId) throws OrderItemIdNotExistsException, OrderIdNotExistsException {
        Order order = orderRepository.findById(id).orElseThrow(OrderIdNotExistsException::new);
        order.removeItem(orderItemId);
        orderRepository.saveAndFlush(order);

    }

    private Order toDomainObject(OrderForm orderForm){
        var order = new Order(Instant.now(), orderForm.getCurrency());
        orderForm.getItems().forEach(item->order.addItem(item.getPizza(), item.getQuantity()));
        return order;
    }
}
