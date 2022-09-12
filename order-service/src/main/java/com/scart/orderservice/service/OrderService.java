package com.scart.orderservice.service;



import java.util.List;

public interface OrderService {

    List<Orders> getAllOrders();

    void placeOrders();

    void deleteOrder();

}
