package com.tscode.shop_clothes.model.dto;


import com.tscode.shop_clothes.entity.User;
import lombok.Data;

@Data
public class CheckoutDto {
private User user;
private Long total;
}
