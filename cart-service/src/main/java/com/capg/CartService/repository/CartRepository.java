package com.capg.CartService.repository;

import com.capg.CartService.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface CartRepository extends MongoRepository<Cart, Integer> {
}
