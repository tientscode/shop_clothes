package com.tscode.shop_clothes.Repository;

import com.tscode.shop_clothes.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByCustomerId(Long id);
}
