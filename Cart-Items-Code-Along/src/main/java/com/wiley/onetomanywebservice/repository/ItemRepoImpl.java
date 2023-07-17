package com.wiley.onetomanywebservice.repository;

import com.wiley.onetomanywebservice.model.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ItemRepoImpl implements ItemRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Item> getAllItems() {
        String query = "Select * from item;";
        return (List<Item>) em.createNativeQuery(query).getResultList();
    }

    @Override
    public Item getItemById(int id) {
        return em.find(Item.class, id);
    }

    @Override
    public Item addItem(Item item) {
        em.persist(item);
        return item;
    }

    @Override
    public Void deleteItem(int id) {
        em.remove(getItemById(id));
        return null;
    }

    @Override
    public Item updateItem(Item item) {
        em.merge(item);
        em.flush();
        return item;
    }
}
