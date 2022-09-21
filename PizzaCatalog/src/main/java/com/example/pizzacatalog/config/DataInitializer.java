package com.example.pizzacatalog.config;

import com.example.pizzacatalog.domain.models.Pizza;
import com.example.pizzacatalog.domain.repository.PizzaRepository;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;


@Component
@AllArgsConstructor
public class DataInitializer {

    private final PizzaRepository pizzaRepository;

    @PostConstruct
    public void initData() {
        Pizza p1 = Pizza.build("Pizza", Money.valueOf(Currency.MKD,500), 10);
        Pizza p2 = Pizza.build("Ice Cream", Money.valueOf(Currency.MKD,100), 5);
        if (pizzaRepository.findAll().isEmpty()) {
            pizzaRepository.saveAll(Arrays.asList(p1,p2));
        }
    }
}

