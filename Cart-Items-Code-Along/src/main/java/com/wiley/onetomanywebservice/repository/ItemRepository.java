package com.wiley.onetomanywebservice.repository;

import com.wiley.onetomanywebservice.model.Item;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository {
    List<Item> getAllItems();
    Item getItemById(int id);
    Item addItem(Item item);
    Void deleteItem(int id);
    Item updateItem(Item item);
}
