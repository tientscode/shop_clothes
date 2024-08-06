package com.tscode.shop_clothes.controller;


import com.tscode.shop_clothes.JpaRepository.CategoryRepository;
import com.tscode.shop_clothes.JpaRepository.ProductRepository;
import com.tscode.shop_clothes.entity.Categories;
import com.tscode.shop_clothes.entity.Products;
import com.tscode.shop_clothes.entity.User;
import com.tscode.shop_clothes.sevice.CodeGenerator;
import com.tscode.shop_clothes.sevice.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashBoardController {
    @Autowired
    SessionService sessionService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;


    @GetMapping
    public String dashboard(Model model) {
        Products products = new Products();
        model.addAttribute("products", products);
        List<Categories> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "dashboard/CreateProduct";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("products") Products products, Model model) {
        products.setCode(CodeGenerator.generateRandomCode(5));
        productRepository.save(products);
        return "redirect:/dashboard";
    }


}
