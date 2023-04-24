package com.pheriwala.app.models;

public class Products {

    String Id, name, qty,price;

    public Products() {
    }

    public Products(String id, String name, String qty, String price) {
        Id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public  String getName() {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
