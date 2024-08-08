package com.tscode.shop_clothes.implement;

import com.tscode.shop_clothes.Repository.RoleRepository;
import com.tscode.shop_clothes.Repository.UserRepository;
import com.tscode.shop_clothes.entity.User;
import com.tscode.shop_clothes.model.dto.LoginDto;
import com.tscode.shop_clothes.model.dto.SingupDto;
import com.tscode.shop_clothes.sevice.AuthInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class AuthInterfaceImpl implements AuthInterface {

    @Autowired
    private UserRepository userRepository;

    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User login(LoginDto loginDto) {
        return Optional.ofNullable(userRepository.findByEmail(loginDto.getUsername()))
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
                    user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
                    user.setActive(true);
                    user.setRole(role);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }


}
