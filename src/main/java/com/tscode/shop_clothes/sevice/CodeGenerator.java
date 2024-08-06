package com.tscode.shop_clothes.sevice;



import java.security.SecureRandom;
import java.util.UUID;

public class CodeGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();

    // Tạo mã code ngẫu nhiên sử dụng UUID
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    // Tạo mã code ngẫu nhiên với độ dài tùy chỉnh
    public static String generateRandomCode(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            code.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return code.toString();
    }
}
