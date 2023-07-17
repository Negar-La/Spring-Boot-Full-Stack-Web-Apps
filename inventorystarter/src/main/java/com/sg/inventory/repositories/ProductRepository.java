package com.sg.inventory.repositories;

import com.sg.inventory.entities.Product;
import com.sg.inventory.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByStore(Store store);
}
