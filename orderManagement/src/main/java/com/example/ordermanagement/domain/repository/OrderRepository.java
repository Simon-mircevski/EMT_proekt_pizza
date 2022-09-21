package com.example.ordermanagement.domain.repository;

import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;

public interface OrderRepository extends JpaRepository<Order, OrderId> {
}
