package com.example.pizzacatalog.services.form;

import com.example.sharedkernel.domain.financial.Money;
import lombok.Data;

@Data
public class PizzaForm {


    private String productName;
    private Money price;
    private int sales;

}
