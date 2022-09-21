package com.example.ordermanagement.domain.valueobjects;

import com.example.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class PizzaId extends DomainObjectId {

    private PizzaId() {
        super(PizzaId.randomId(PizzaId.class).getId());
    }

    public PizzaId(String uuid) {
        super(uuid);
    }

}
