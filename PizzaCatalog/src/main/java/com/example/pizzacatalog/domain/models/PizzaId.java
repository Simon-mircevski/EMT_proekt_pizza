package com.example.pizzacatalog.domain.models;

import com.example.sharedkernel.domain.base.DomainObjectId;
import lombok.NonNull;

public class PizzaId extends DomainObjectId {
    private PizzaId() {
        super(PizzaId.randomId(PizzaId.class).getId());
    }

    public PizzaId(@NonNull String uuid) {
        super(uuid);
    }

    public static PizzaId of(String uuid) {
        PizzaId p = new PizzaId(uuid);
        return p;
    }

}
