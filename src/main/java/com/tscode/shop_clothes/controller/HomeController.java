package com.tscode.shop_clothes.controller;

import com.tscode.shop_clothes.Repository.CartRepository;
import com.tscode.shop_clothes.Repository.ProductRepository;
import com.tscode.shop_clothes.configuration.CustomUserDetails;
import com.tscode.shop_clothes.entity.Cart;
import com.tscode.shop_clothes.entity.Products;
import com.tscode.shop_clothes.model.dto.OrderDto;
import com.tscode.shop_clothes.sevice.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    SessionService sessionService;
    @Autowired
    CartRepository cartRepository;

    @GetMapping
    public String home(Model model) {
        List<Products> activeProducts = productRepository.findAll().stream().filter(Products::isActive).collect(Collectors.toList());
        model.addAttribute("products", activeProducts);


        return "Homepage";
    }

}
