package com.capg.CartService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "carts")
public class Cart {

    @Transient
    public static final String SEQUENCE_NAME = "cart_sequence";

    @Id
    private int cartId;
    private double totalPrice;
    private List<Items> productId;
    public Cart(int cartId, List<Items> productId) {
        this.cartId = cartId;
        this.productId = productId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<Items> getProductId() {
        return productId;
    }

    public void setProductId(List<Items> productId) {
        this.productId = productId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "productId=" + productId +
                ", cartId=" + cartId +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return getCartId() == cart.getCartId() && Double.compare(cart.getTotalPrice(), getTotalPrice()) == 0 && getProductId().equals(cart.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getCartId(), getTotalPrice());
    }
}
