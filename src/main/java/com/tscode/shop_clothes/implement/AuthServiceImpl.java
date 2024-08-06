package com.tscode.shop_clothes.implement;

import com.tscode.shop_clothes.JpaRepository.RoleRepository;
import com.tscode.shop_clothes.JpaRepository.UserRepository;
import com.tscode.shop_clothes.entity.Role;
import com.tscode.shop_clothes.entity.User;
import com.tscode.shop_clothes.model.dto.LoginDto;
import com.tscode.shop_clothes.model.dto.SingupDto;
import com.tscode.shop_clothes.sevice.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User login(LoginDto loginDto) {
        return Optional.ofNullable(userRepository.findByEmail(loginDto.getEmail()))
                .filter(user -> loginDto.getPassword().equals(user.getPassword()))
                .map(user -> {
                    if (user.getActive()) {
                        return user;
                    } else {
                        throw new RuntimeException("My account is locked !");
                    }
                })
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
    }


    @Override
    public User singup(SingupDto signupDto) {
        return Optional.ofNullable(roleRepository.findByName("USER"))
                .map(role -> {
                    User user = new User();
                    user.setEmail(signupDto.getEmail());
                    user.setName(signupDto.getName());
                    user.setPassword(signupDto.getPassword());
                    user.setActive(true);
                    user.setRole(role);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("Role 'USER' not found"));
    }



}
