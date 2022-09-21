package com.example.pizzacatalog.services;

import com.example.pizzacatalog.domain.models.Pizza;
import com.example.pizzacatalog.domain.models.PizzaId;
import com.example.pizzacatalog.services.form.PizzaForm;

import java.util.List;

public interface PizzaService {
    Pizza findById(PizzaId id);
    Pizza createPizza(PizzaForm form);
    Pizza orderItemCreated(PizzaId pizzaId, int quantity);
    Pizza orderItemRemoved(PizzaId pizzaId, int quantity);
    List<Pizza> getAll();
}
