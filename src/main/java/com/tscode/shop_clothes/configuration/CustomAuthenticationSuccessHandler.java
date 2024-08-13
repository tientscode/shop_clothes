//package com.tscode.shop_clothes.configuration;
//
//import com.tscode.shop_clothes.sevice.SessionService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//    @Autowired
//    SessionService sessionService;
//
////    @Override
////    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
////        String username = authentication.getName();
////        System.out.println(username);
////        String token = JwtUtil.generateToken(username);
////        sessionService.set("token", token);
////
////        // Lưu token vào localStorage bằng cách trả về token cho frontend qua response
//////        response.setContentType("application/json");
//////        response.getWriter().write("{\"token\":\"" + token + "\"}");
//////        response.getWriter().flush();
////    }
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
//            throws IOException {
//        // Tạo token (JWT hoặc bất kỳ loại token nào bạn sử dụng)
//        String token = JwtUtil.generateToken(authentication.getName());
//
//        // Tạo cookie với token
//        Cookie cookie = new Cookie("authToken", token);
//        cookie.setMaxAge(24 * 60 * 60); // Đặt thời gian tồn tại cookie là 24 giờ
//        cookie.setHttpOnly(true); // Ngăn chặn truy cập cookie từ JavaScript
//        cookie.setPath("/"); // Cookie có hiệu lực cho toàn bộ ứng dụng
//
//        // Thêm cookie vào phản hồi
//        response.addCookie(cookie);
//
//        // Chuyển hướng sau khi đăng nhập thành công
//        response.sendRedirect("/home");
//    }
//}
