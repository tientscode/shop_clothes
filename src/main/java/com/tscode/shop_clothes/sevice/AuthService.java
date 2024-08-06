package com.tscode.shop_clothes.sevice;

import com.tscode.shop_clothes.entity.Products;
import com.tscode.shop_clothes.entity.Role;
import com.tscode.shop_clothes.entity.User;
import com.tscode.shop_clothes.model.dto.LoginDto;
import com.tscode.shop_clothes.model.dto.ProductDto;
import com.tscode.shop_clothes.model.dto.SingupDto;


public interface AuthService {
    User login(LoginDto loginDto);
    User singup(SingupDto singupDto);
}
