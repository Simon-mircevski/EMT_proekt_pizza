package com.example.ordermanagement.domain.model;

import com.example.ordermanagement.domain.valueobjects.PizzaId;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.base.DomainObject;
import com.example.sharedkernel.domain.base.DomainObjectId;
import com.example.sharedkernel.domain.financial.Money;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="order_item")
@Getter
public class OrderItem extends AbstractEntity<OrderItemId> {


    private Money itemPrice;

    @Column(name="qty", nullable = false)
    private int quantity;

    @AttributeOverride(name="id", column = @Column(name="product_id", nullable = false))
    private PizzaId pizzaId;

    public OrderItem(@NonNull PizzaId pizzaId, @NonNull Money itemPrice, int quantity) {
        super(DomainObjectId.randomId(OrderItemId.class));
        this.pizzaId = pizzaId;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    public Money subtotal(){
        return itemPrice.multiply(quantity);
    }
}
