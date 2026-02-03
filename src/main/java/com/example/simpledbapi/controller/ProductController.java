package com.example.simpledbapi.controller;

import com.example.simpledbapi.dao.ProductDAO;
import com.example.simpledbapi.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductDAO dao;

    public ProductController(ProductDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Product> all()  {
        return dao.findAll();
    }

    @PostMapping
    public Product create(@RequestBody Product p)  {
        Integer id = dao.add(p.getName(), p.getPrice());
        return new Product(id, p.getName(), p.getPrice());
    }

    @PutMapping("/{id}/price")
    public String updatePrice(@PathVariable Integer id, @RequestBody Product p) {
        int updated = dao.updatePrice(id, p.getPrice());
        return (updated == 1) ? "UPDATED" : "NOT FOUND";
    }
}