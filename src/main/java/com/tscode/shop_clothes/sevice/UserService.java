package com.tscode.shop_clothes.sevice;


import com.tscode.shop_clothes.Repository.UserRepository;
import com.tscode.shop_clothes.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User updateUserById(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Cập nhật các trường từ updatedUser vào existingUser
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setActive(updatedUser.getActive());
        // ... các trường khác

        return userRepository.save(existingUser);
    }
}
