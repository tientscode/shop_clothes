package com.tscode.shop_clothes.Repository;

import com.tscode.shop_clothes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User save(User user);
}
