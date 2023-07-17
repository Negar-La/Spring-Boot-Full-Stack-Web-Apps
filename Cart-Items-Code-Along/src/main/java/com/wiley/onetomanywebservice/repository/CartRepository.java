package com.wiley.onetomanywebservice.repository;

import com.wiley.onetomanywebservice.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository {
    List<Cart> getAllCarts();
    Cart getCartById(int id);
    Void addCart(Cart cart);
    Void deleteCart(int id);
    Cart updateCart(Cart cart);
}
