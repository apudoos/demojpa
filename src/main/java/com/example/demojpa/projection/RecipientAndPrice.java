package com.example.demojpa.projection;

import java.math.BigDecimal;

public class RecipientAndPrice {

    String name;
    BigDecimal price;

    public RecipientAndPrice(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
