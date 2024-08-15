package com.tscode.shop_clothes.controller;


import com.tscode.shop_clothes.Repository.CategoryRepository;
import com.tscode.shop_clothes.Repository.OderSuccessfullyRepository;
import com.tscode.shop_clothes.Repository.ProductRepository;
import com.tscode.shop_clothes.Repository.UserRepository;
import com.tscode.shop_clothes.entity.Categories;
import com.tscode.shop_clothes.entity.OrderSuccessfully;
import com.tscode.shop_clothes.entity.Products;
import com.tscode.shop_clothes.entity.User;
import com.tscode.shop_clothes.sevice.CodeGenerator;
import com.tscode.shop_clothes.sevice.SessionService;
import com.tscode.shop_clothes.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/dashboard")
public class DashBoardController {
    @Autowired
    SessionService sessionService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    OderSuccessfullyRepository oderRepository;


    @GetMapping
    public String dashboard(Model model) {
        Products products = new Products();
        model.addAttribute("products", products);
        List<Products> latestProducts = productRepository.getLatestProducts(productRepository.findAll());
        model.addAttribute("latestProducts", latestProducts);
        List<Categories> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "dashboard/CreateProduct";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("products") Products products, Model model) {
        products.setCode(CodeGenerator.generateRandomCode(5));
        if (products.getCreatedAt() == null) {
            products.setCreatedAt(LocalDateTime.now());
        }
        products.setQtySold(0);
        productRepository.save(products);
        return "redirect:/dashboard";
    }

    @GetMapping("/UserManagement")
    public String userManagement(Model model) {
        model.addAttribute("allUsers", userRepository.findAll());
        return "dashboard/UserManagement";
    }

    @PostMapping("/updateIdUser")
    public String updateUser(@RequestParam Long id, @RequestParam(required = false) Boolean active) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(active != null ? active : false);
        userRepository.save(user);
        return "redirect:/dashboard/UserManagement"; // Điều hướng lại trang quản lý người dùng
    }

    @PostMapping("/updateIdProduct")
    public String update(@ModelAttribute("clickProducts") Products products) {
        productRepository.save(products);

        return "redirect:/dashboard/ProductManagement";
    }


    @GetMapping("/ProductManagement")
    public String productManagement(Model model, @RequestParam(required = false) Long id) {
        if (id != null) {
            Products clickProducts = productRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            model.addAttribute("clickProducts", clickProducts);
        } else {
            model.addAttribute("clickProducts", null);
        }
        model.addAttribute("allProducts", productRepository.findAll());

        return "dashboard/ProductManagement";
    }

    @GetMapping("/Order")
    public String order(Model model) {
        model.addAttribute("orders", oderRepository.findAll());
        return "dashboard/OrderManagement";
    }

    @PostMapping("/Order/adCheck")
    public String AdCheck(Model model,@RequestParam int id, @RequestParam(required = false) Boolean active) {
        System.out.println(id);
        System.out.println(active);
        OrderSuccessfully order = oderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setAdCheck(active != null ? active : false);
        oderRepository.save(order);
        return "dashboard/OrderManagement";
    }


}
