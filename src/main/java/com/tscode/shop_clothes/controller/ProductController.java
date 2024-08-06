package com.tscode.shop_clothes.controller;

import com.tscode.shop_clothes.JpaRepository.ProductRepository;
import com.tscode.shop_clothes.entity.Products;
import com.tscode.shop_clothes.model.Notification.StatusAPI;
import com.tscode.shop_clothes.model.dto.ProductDto;
import com.tscode.shop_clothes.sevice.CodeGenerator;
import com.tscode.shop_clothes.sevice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/api/product")
public class ProductController {
//    @Autowired
//    ProductService productService;

    @Autowired
    ProductRepository productRepository;

//    @PostMapping("/add")
//    public ResponseEntity<StatusAPI<Products>> addProduct(@RequestBody ProductDto productDto) {
//        try {
//            productDto.setCode(CodeGenerator.generateRandomCode(5));
//            Products products = productService.save(productDto);
//            StatusAPI<Products> response = new StatusAPI<>(products, "thêm sản phẩm thành cônng", HttpStatus.OK);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            StatusAPI<Products> response = new StatusAPI<>(null, e.getMessage(), HttpStatus.BAD_REQUEST);
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    }


    @GetMapping("/all")
    public ResponseEntity<StatusAPI<List<Products>>> getAllProducts(@RequestParam(required = false) String sort) {
        try {
            List<Products> products = productRepository.findAll();
            String message;
            if ("top".equals(sort)) {
                products.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
                message = "Xếp theo giá giảm dần";
            } else {
                products.sort(Comparator.comparingDouble(Products::getPrice));
                message = "Xếp theo giá tăng dần";
            }
            StatusAPI<List<Products>> response = new StatusAPI<>(products, message, HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            StatusAPI<List<Products>> response = new StatusAPI<>(null, "Failed to retrieve products: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
