package com.tscode.shop_clothes.controller;

import com.tscode.shop_clothes.Repository.ProductRepository;
import com.tscode.shop_clothes.configuration.CustomUserDetails;
import com.tscode.shop_clothes.entity.Products;
import com.tscode.shop_clothes.sevice.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @Autowired
    SessionService sessionService;

    @GetMapping
    public String home(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails != null) {
            sessionService.set("user", userDetails);
        }
        List<Products> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "Homepage";
    }
}
