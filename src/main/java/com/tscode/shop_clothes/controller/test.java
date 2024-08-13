package com.tscode.shop_clothes.controller;


import com.tscode.shop_clothes.Repository.ProductRepository;
import com.tscode.shop_clothes.entity.Products;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class test {

    @Autowired
    ProductRepository productRepository;


    @GetMapping("/cart/{id}")
    public Products getProductById(@PathVariable("id") Long id) {
        return productRepository.findById(id).orElse(null);
    }



}
