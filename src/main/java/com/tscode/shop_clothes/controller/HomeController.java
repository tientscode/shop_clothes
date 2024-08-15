package com.tscode.shop_clothes.controller;

import com.tscode.shop_clothes.Repository.CartRepository;
import com.tscode.shop_clothes.Repository.ProductRepository;
import com.tscode.shop_clothes.Repository.UserRepository;
import com.tscode.shop_clothes.configuration.CustomUserDetails;
import com.tscode.shop_clothes.entity.Cart;
import com.tscode.shop_clothes.entity.Products;
import com.tscode.shop_clothes.entity.User;
import com.tscode.shop_clothes.model.dto.OrderDto;
import com.tscode.shop_clothes.sevice.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String home(Model model) {
        Pageable topFour = PageRequest.of(0, 4);
        List<Products> top4BestSellingProducts = productRepository.findTop4ByQtySold(topFour);
        for (Products p : top4BestSellingProducts) {
            System.out.println(p);
        }
        model.addAttribute("top4BestSellingProducts", top4BestSellingProducts);
        List<Products> activeProducts = productRepository.findAll().stream().filter(Products::isActive).collect(Collectors.toList());
        model.addAttribute("products", activeProducts);

        return "Homepage";
    }

    @GetMapping("/userInformation")
    public String userInformation(Model model) {
        return "componnent/UserInformation";
    }

    @PostMapping("/userInformation/update")
    public String updateUserInformation(@ModelAttribute("user") User users, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByEmail(users.getEmail());
        user.setName(users.getName());
        user.setAddress(users.getAddress());
        user.setPhone(users.getPhone());
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("message", "Cập nhật thông tin thành công!");
        return "redirect:/home/userInformation";
    }
}
