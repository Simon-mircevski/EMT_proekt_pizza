package com.example.ordermanagement.domain.exceptions;

public class OrderIdNotExistsException extends RuntimeException{
    public OrderIdNotExistsException(){
        super("Order ID not found");
    }
}
