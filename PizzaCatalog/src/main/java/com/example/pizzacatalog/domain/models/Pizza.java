package com.example.pizzacatalog.domain.models;

import com.example.pizzacatalog.domain.valueobjects.Quantity;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.financial.Money;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "pizza")
@Getter
public class Pizza extends AbstractEntity<PizzaId> {
    private String PizzaName;

    private int sales;

    private String Description;

    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="price_amount")),
            @AttributeOverride(name="currency", column = @Column(name="price_currency"))
    })
    private Money price;

    private Pizza(){
        super(PizzaId.randomId(PizzaId.class));
    }

    public static Pizza build(String pizzaName, Money price, int sales){
        Pizza p = new Pizza();
        p.price = price;
        p.PizzaName = pizzaName;
        p.sales = sales;
        return p;
    }

    public void addSales(int qty){
        this.sales = this.sales-qty;
    }

    public void removeSales(int qty){
        this.sales -= qty;
    }
}
