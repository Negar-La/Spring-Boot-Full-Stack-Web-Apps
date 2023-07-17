package com.wiley.onetomanywebservice.repository;


import com.wiley.onetomanywebservice.model.Cart;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CartRepoImpl implements CartRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Cart> getAllCarts() {
        String query = "Select * from cart;";
        return (List<Cart>) em.createNativeQuery(query).getResultList();
    }

    @Override
    public Cart getCartById(int id) {
        return em.find(Cart.class, id);
    }

    @Override
    public Void addCart(Cart cart) {
        em.persist(cart);
        return null;
    }

    @Override
    public Void deleteCart(int id) {
        em.remove(getCartById(id));
        return null;
    }

    @Override
    public Cart updateCart(Cart cart) {
        em.merge(cart);
        em.flush();
        return cart;
    }
}
