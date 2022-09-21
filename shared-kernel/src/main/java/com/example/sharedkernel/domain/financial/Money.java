package com.example.sharedkernel.domain.financial;

import com.example.sharedkernel.domain.base.ValueObject;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;



@Embeddable
@Getter
public class Money implements ValueObject {

    @Enumerated(value = EnumType.STRING)
    private final Currency currency;

    private final double amount;

    protected Money(){
        this.currency = null;
        this.amount = 0.0;
    }

    public Money(@NonNull Currency currency,@NonNull double amount){
        this.currency = currency;
        this.amount = amount;
    }

    public static Money valueOf(Currency currency, int amount){ return new Money(currency, amount);}

    public Money add(Money money){
        if(!currency.equals(money.currency)){
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Money(currency, amount+money.amount);
    }

    public Money subtract(Money money){
        if(!currency.equals(money.currency)){
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Money(currency, amount - money.amount);
    }

    public Money multiply(int n){
        return new Money(currency,amount*n);
    }
}
