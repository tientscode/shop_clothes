package com.tscode.shop_clothes.controller;


import com.tscode.shop_clothes.entity.User;
import com.tscode.shop_clothes.model.dto.LoginDto;
import com.tscode.shop_clothes.sevice.AuthInterface;
import com.tscode.shop_clothes.sevice.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AuthController {

    @Autowired
    private AuthInterface authInterface;
    @Autowired
    SessionService sessionService;

//    @PostMapping("/login")
//    public ResponseEntity<StatusAPI<User>> login(@RequestBody LoginDto loginDto) {
//        try {
//            User user = authService.login(loginDto);
//            StatusAPI<User> response = new StatusAPI<>(user, "Success login", HttpStatus.OK);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }
//        catch (RuntimeException e) {
//            StatusAPI<User> response = new StatusAPI<>(null, e.getMessage(), HttpStatus.BAD_REQUEST);
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @PostMapping("/singup")
//    public ResponseEntity<StatusAPI<User>> singup(@RequestBody SingupDto singupDto) {
//        try {
//            User user =authService.singup(singupDto);
//            StatusAPI<User> response = new StatusAPI<>(user, "Sign Up Success ", HttpStatus.OK);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }
//        catch (RuntimeException e) {
//            StatusAPI<User> response = new StatusAPI<>(null, e.getMessage(), HttpStatus.BAD_REQUEST);
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping("/login")
    public String home(Model model) {
        LoginDto loginDto = new LoginDto();
        model.addAttribute("loginDto", loginDto);
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginDto") LoginDto loginDto, Model model) {
        try {
            User user = authInterface.login(loginDto);
            sessionService.set("user", user);
            return "redirect:/home";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "auth/login";
        }
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @GetMapping("/send/mail")
    public String senmail() {
        return "auth/Sendmail_Otp";
    }


}
