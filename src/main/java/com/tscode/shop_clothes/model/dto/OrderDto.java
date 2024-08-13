package com.tscode.shop_clothes.model.dto;

import com.tscode.shop_clothes.entity.Cart;
import lombok.Data;

@Data
public class OrderDto {
    private Cart cart;
    private String image;
    private String name;
}
