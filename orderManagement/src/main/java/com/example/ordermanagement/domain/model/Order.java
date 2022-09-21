package com.example.ordermanagement.domain.model;

import com.example.ordermanagement.domain.valueobjects.Pizza;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="orders")
@Getter
public class Order extends AbstractEntity<OrderId> {

    private Instant orderedOn;

    @Enumerated(value = EnumType.STRING)
    private OrderState orderState;

    @Column(name="order_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
    fetch = FetchType.EAGER)
    private Set<OrderItem> orderItemList = new HashSet<>();

    private Order(){
        super(OrderId.randomId(OrderId.class));
    }

    public Order(Instant now, com.example.sharedkernel.domain.financial.Currency currency) {
        super(OrderId.randomId(OrderId.class));
        this.orderedOn = now;
        this.currency = currency;
    }

    public Money total(){
        return orderItemList.stream().map(OrderItem ::subtotal).reduce(new Money(currency, 0), Money::add);
    }

    public OrderItem addItem(@NonNull Pizza pizza, int quantity){
        Objects.requireNonNull(pizza, "pizza must not be null");
        var item = new OrderItem(pizza.getId(), pizza.getPrice(), quantity);
        orderItemList.add(item);
        return item;
    }

    public void removeItem(@NonNull OrderItemId orderItemId){
        Objects.requireNonNull(orderItemId,"Order Item must not be null");
        orderItemList.removeIf(r->r.getId().equals(orderItemId));
    }
}
