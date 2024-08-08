package com.tscode.shop_clothes.configuration;

import com.tscode.shop_clothes.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }


//     nếu nhiều quyền và phải thêm ROLE khi dùng phương thức này
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toSet());
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        log.warn(user.getRole().getName());
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getActive();
    }

    // Thêm các phương thức để lấy thông tin bổ sung
    public String getName() {
        return user.getName();
    }

    public String getImage() {
        return user.getImage();
    }

    public String getPhone() {
        return user.getPhone();
    }

    public String getAddress() {
        return user.getAddress();
    }
}
