package com.tscode.shop_clothes.controller;

import com.tscode.shop_clothes.Repository.ProductRepository;
import com.tscode.shop_clothes.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    ProductRepository productRepository;


    @GetMapping
    public String home(Model model) {
        List<Products> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "Homepage";
    }
}
