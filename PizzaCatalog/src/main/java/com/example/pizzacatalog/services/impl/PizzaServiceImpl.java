package com.example.pizzacatalog.services.impl;

import com.example.pizzacatalog.domain.exceptions.PizzaNotFoundException;
import com.example.pizzacatalog.domain.models.Pizza;
import com.example.pizzacatalog.domain.models.PizzaId;
import com.example.pizzacatalog.domain.repository.PizzaRepository;
import com.example.pizzacatalog.services.PizzaService;
import com.example.pizzacatalog.services.form.PizzaForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;

    @Override
    public Pizza findById(PizzaId id) {
        return pizzaRepository.findById(id).orElseThrow(PizzaNotFoundException:: new);
    }

    @Override
    public Pizza createPizza(PizzaForm form) {
        Pizza p = Pizza.build(form.getProductName(), form.getPrice(), form.getSales());
        pizzaRepository.save(p);
        return p;
    }

    @Override
    public Pizza orderItemCreated(PizzaId pizzaId, int quantity) {
        Pizza p = pizzaRepository.findById(pizzaId).orElseThrow(PizzaNotFoundException::new);
        p.addSales(quantity);
        pizzaRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public Pizza orderItemRemoved(PizzaId pizzaId, int quantity) {
        Pizza p = pizzaRepository.findById(pizzaId).orElseThrow(PizzaNotFoundException::new);
        p.removeSales(quantity);
        pizzaRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public List<Pizza> getAll() {
        return pizzaRepository.findAll();
    }
}
