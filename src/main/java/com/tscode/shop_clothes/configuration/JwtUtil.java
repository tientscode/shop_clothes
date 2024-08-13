//package com.tscode.shop_clothes.configuration;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//import java.util.Date;
//
//public class JwtUtil {
//    private static final String SECRET_KEY = "your_secret_key"; // Thay thế bằng khóa bí mật của bạn
//
//    public static String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Token hết hạn sau 1 ngày
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//    }
//}
