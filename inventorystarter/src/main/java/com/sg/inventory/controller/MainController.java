package com.sg.inventory.controller;

import com.sg.inventory.entities.Product;
import com.sg.inventory.entities.Store;
import com.sg.inventory.entities.Supplier;
import javax.servlet.http.HttpServletRequest;

import com.sg.inventory.repositories.ProductRepository;
import com.sg.inventory.repositories.StoreRepository;
import com.sg.inventory.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 *
 * @author kylerudy
 */
@Controller
public class MainController {

    @Autowired
    ProductRepository products;

    @Autowired
    StoreRepository stores;

    @Autowired
    SupplierRepository suppliers;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("stores", stores.findAll());
        return "index";
    }
    
    @PostMapping("/addStore")
    public String addStore(Store store) {
        stores.save(store);
        return "redirect:/";
    }
    
    @GetMapping("/viewInventory")
    public String viewInventory(Integer id, Model model) {

        Store store = stores.findById(id).orElse(null);
        List<Product> productList = products.findByStore(store);

        model.addAttribute("store", store);
        model.addAttribute("products", productList);

        return "inventory";
    }
    
    @PostMapping("/addProduct")
    public String addProduct(Product product, HttpServletRequest request) {
        int storeId = Integer.parseInt(request.getParameter("storeId"));
        Store store = stores.findById(storeId).orElse(null);
        product.setStore(store);

        products.save(product);
        return "redirect:/viewInventory?id=" + storeId;
    }
    
    @GetMapping("/deleteProduct")
    public String deleteProduct(Integer id, Integer storeId) {
        products.deleteById(id);
        return "redirect:/viewInventory?id=" + storeId;
    }
    
    @GetMapping("/product")
    public String displayProduct(Integer id, Model model) {
        Product product = products.findById(id).orElse(null);
        List<Supplier> supplierList = suppliers.findAll();

        model.addAttribute("product", product);
        model.addAttribute("suppliers", supplierList);

        return "product";
    }
    
    @PostMapping("/addSupplier")
    public String addSupplier(Supplier supplier, HttpServletRequest request) {
        int productId = Integer.parseInt(request.getParameter("productId"));
        Product product = products.findById(productId).orElse(null);

        supplier = suppliers.save(supplier);

        product.getSuppliers().add(supplier);
        products.save(product);

        return "redirect:/product?id=" + productId;
    }
    
    @PostMapping("/addExistingSupplier")
    public String addExistingSupplier(Integer productId, Integer supplierId) {
        Product product = products.findById(productId).orElse(null);
        Supplier supplier = suppliers.findById(supplierId).orElse(null);

        product.getSuppliers().add(supplier);
        products.save(product);

        return "redirect:/product?id=" + productId;
    }
    
    @GetMapping("/removeSupplier")
    public String removeSupplier(Integer productId, Integer supplierId) {
        Product product = products.findById(productId).orElse(null);
        Supplier supplier = suppliers.findById(supplierId).orElse(null);

        product.getSuppliers().remove(supplier);
        products.save(product);

        return "redirect:/product?id=" + productId;
    }
    
    @GetMapping("/supplier")
    public String displaySupplier(Integer id, Model model) {

        Supplier supplier = suppliers.findById(id).orElse(null);

        model.addAttribute("supplier", supplier);

        return "supplier";
    }
}
