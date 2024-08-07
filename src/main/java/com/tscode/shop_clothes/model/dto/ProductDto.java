package com.tscode.shop_clothes.model.dto;


import com.tscode.shop_clothes.entity.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    String sex;
    String time;
    String price;
    Categories category;
}
