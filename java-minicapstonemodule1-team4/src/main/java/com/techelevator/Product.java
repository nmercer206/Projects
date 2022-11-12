package com.techelevator;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Product {
    // Properties

    private String name;
    private double price;
    private int stock;
    private String type;

    // Constructors
    public Product(String name, double price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = 5;
        }


    // G&S

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getType() {
        return type;
    }
    // Methods



    public void decreaseStock() {
        this.stock -= 1;
    }


}
