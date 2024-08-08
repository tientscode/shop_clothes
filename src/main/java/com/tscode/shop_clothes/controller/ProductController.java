package com.tscode.shop_clothes.controller;

import com.tscode.shop_clothes.Repository.CategoryRepository;
import com.tscode.shop_clothes.Repository.ProductRepository;
import com.tscode.shop_clothes.entity.Categories;
import com.tscode.shop_clothes.entity.Products;
import com.tscode.shop_clothes.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private ProductRepository productRepository;

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


//    @GetMapping("/all")
//    public ResponseEntity<StatusAPI<List<Products>>> getAllProducts(@RequestParam(required = false) String sort) {
//        try {
//            List<Products> products = productRepository.findAll();
//            String message;
//            if ("top".equals(sort)) {
//                products.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
//                message = "Xếp theo giá giảm dần";
//            } else {
//                products.sort(Comparator.comparingDouble(Products::getPrice));
//                message = "Xếp theo giá tăng dần";
//            }
//            StatusAPI<List<Products>> response = new StatusAPI<>(products, message, HttpStatus.OK);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            StatusAPI<List<Products>> response = new StatusAPI<>(null, "Failed to retrieve products: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("category", categoryRepository.findAll());
        List<Products> products;
        if (model.asMap().containsKey("products")) {
            products = (List<Products>) model.asMap().get("products");
        } else {
            products = productRepository.findAll();
        }
        model.addAttribute("products", products);
        return "componnent/Product_Category";
    }


    @PostMapping
    public String findProduct(@ModelAttribute("productDto") ProductDto productDto, RedirectAttributes redirectAttributes) {
        List<Products> products;

        System.out.println(productDto.getCategory());
        if (productDto.getCategory() == null) {
            products = productRepository.findAll();

        } else {
            products = productRepository.findByCategory(productDto.getCategory());
        }


        if (productDto.getPrice() != null) {
            if ("4".equals(productDto.getPrice())) {
                products.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice())); // Giá cao đến thấp
            } else if ("5".equals(productDto.getPrice())) {
                products.sort(Comparator.comparingDouble(Products::getPrice)); // Giá thấp đến cao
            }
        }

        if (productDto.getTime() != null) {
            if ("New".equalsIgnoreCase(productDto.getTime())) {
                products.sort((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt())); // Mới nhất trước
            } else if ("Old".equalsIgnoreCase(productDto.getTime())) {
                products.sort(Comparator.comparing(Products::getCreatedAt)); // Cũ nhất trước
            }
        }

        if (productDto.getSex() != null) {
            if ("male".equalsIgnoreCase(productDto.getSex())) {
                products = products.stream()
                        .filter(p -> "male".equalsIgnoreCase(p.getTargetCustomer()))
                        .toList();
            } else if ("female".equalsIgnoreCase(productDto.getSex())) {
                products = products.stream()
                        .filter(p -> "female".equalsIgnoreCase(p.getTargetCustomer()))
                        .toList();
            }
        }
        redirectAttributes.addFlashAttribute("products", products);
        return "redirect:/product";
    }


}
