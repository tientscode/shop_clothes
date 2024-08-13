//package com.tscode.shop_clothes.configuration;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
//import org.springframework.security.web.savedrequest.SavedRequest;
//import java.io.IOException;
//
//public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response,
//                         AuthenticationException authException) throws IOException {
//
//        // Lấy URL mà người dùng yêu cầu trước khi bị chuyển hướng đến trang đăng nhập
//        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
//        if (savedRequest != null) {
//            String targetUrl = savedRequest.getRedirectUrl();
//            System.out.println("URL trước khi đăng nhập: " + targetUrl);
//            // Bạn có thể lưu URL này vào session hoặc xử lý khác tùy vào nhu cầu
//        }
//
//        // Chuyển hướng đến trang đăng nhập
//        response.sendRedirect("/login");
//    }
//}
