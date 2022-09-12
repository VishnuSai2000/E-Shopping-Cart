package com.capg.CartService.service;

import com.capg.CartService.exception.CartException;
import com.capg.CartService.model.Cart;

import java.util.List;

public interface CartService {
    public Cart getCartById(int cartId) throws CartException;
    public Cart updateCart(int cartId, Cart cart) throws CartException;
    public List<Cart> getAllCarts() throws CartException;
    public double cartTotal(Cart total);
    public Cart addCart(Cart cart);
}
