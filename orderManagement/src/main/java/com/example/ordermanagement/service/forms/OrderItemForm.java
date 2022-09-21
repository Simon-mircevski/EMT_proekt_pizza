package com.example.ordermanagement.service.forms;

import com.example.ordermanagement.domain.valueobjects.Pizza;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderItemForm {
    @NotNull
    private Pizza pizza;

    @Min(1)
    private int quantity = 1;
}
