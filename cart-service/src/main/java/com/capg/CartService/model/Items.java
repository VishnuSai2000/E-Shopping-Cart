package com.capg.CartService.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;


@Document(collection = "itemss")
public class Items {
    private String productName;
    private double price;
    private int quantity;

    public Items(String productName, double price, int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Items{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Items)) return false;
        Items items = (Items) o;
        return Double.compare(items.price, price) == 0 && quantity == items.quantity && Objects.equals(productName, items.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, price, quantity);
    }
}
