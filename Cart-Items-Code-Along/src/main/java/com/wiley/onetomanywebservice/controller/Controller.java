package com.wiley.onetomanywebservice.controller;

import com.wiley.onetomanywebservice.model.Cart;
import com.wiley.onetomanywebservice.model.Item;
import com.wiley.onetomanywebservice.repository.CartRepository;
import com.wiley.onetomanywebservice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class Controller {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> cartList = cartRepository.getAllCarts();
        return ResponseEntity.status(HttpStatus.OK).body(cartList);
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable("id") Integer id) {
        Cart cart = cartRepository.getCartById(id);
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PostMapping("/cart")
    public ResponseEntity<Void> cartAdd(@RequestBody Cart cart) {
        cartRepository.addCart(cart);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") Integer id) {
        cartRepository.deleteCart(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/cart/update")
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
        cartRepository.updateCart(cart);
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }


    // **********************************************************************

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> itemList = itemRepository.getAllItems();
        return ResponseEntity.status(HttpStatus.OK).body(itemList);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Integer id) {
        Item item = itemRepository.getItemById(id);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @PostMapping("/item")
    public ResponseEntity<Void> itemAdd(@RequestBody Item item) {
        itemRepository.addItem(item);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") Integer id) {
        itemRepository.deleteItem(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/item/update")
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        itemRepository.updateItem(item);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }
}
