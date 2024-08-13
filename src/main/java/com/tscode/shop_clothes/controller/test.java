package com.tscode.shop_clothes.controller;


import com.tscode.shop_clothes.Repository.CartRepository;
import com.tscode.shop_clothes.Repository.ProductRepository;
import com.tscode.shop_clothes.configuration.CustomUserDetails;
import com.tscode.shop_clothes.entity.Cart;
import com.tscode.shop_clothes.entity.Products;
import com.tscode.shop_clothes.model.dto.OrderDto;
import com.tscode.shop_clothes.sevice.SessionService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class test {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private SessionService sessionService;

    @ModelAttribute
    public void addAttributes(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails != null) {
            sessionService.set("user", userDetails);

            List<Cart> listCart = cartRepository.findByCustomerId(userDetails.getId());
            List<OrderDto> orderDtos = listCart.stream().map(cart -> {
                OrderDto dto = new OrderDto();
                dto.setCart(cart);

                String image = productRepository.findById(cart.getProductId())
                        .map(Products::getImage)
                        .orElse("");
                dto.setImage(image);

                String name = productRepository.findById(cart.getProductId())
                        .map(Products::getName)
                        .orElse("");
                dto.setName(name);

                return dto;
            }).collect(Collectors.toList());
            model.addAttribute("listCart", orderDtos);
            model.addAttribute("listCartSize", orderDtos.size());
        }
    }
}

