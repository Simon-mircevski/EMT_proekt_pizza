package com.example.pizzacatalog.xport.rest;

import com.example.pizzacatalog.domain.models.Pizza;
import com.example.pizzacatalog.services.PizzaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pizza")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class PizzaResource {
    private final PizzaService pizzaService;

    @GetMapping
    public List<Pizza> getAll(){
        return pizzaService.getAll();
    }
}
