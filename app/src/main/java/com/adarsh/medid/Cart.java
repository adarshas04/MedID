package com.adarsh.medid;

public class Cart {

    String name, qty;

    public Cart() {

    }

    public Cart(String name, String qty) {

        this.name = name;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
