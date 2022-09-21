package com.example.pizzacatalog.domain.repository;

import com.example.pizzacatalog.domain.models.Pizza;
import com.example.pizzacatalog.domain.models.PizzaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, PizzaId> {
}
