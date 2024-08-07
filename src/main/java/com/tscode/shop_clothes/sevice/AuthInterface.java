package com.tscode.shop_clothes.sevice;

import com.tscode.shop_clothes.entity.User;
import com.tscode.shop_clothes.model.dto.LoginDto;
import com.tscode.shop_clothes.model.dto.SingupDto;


public interface AuthInterface {
    User login(LoginDto loginDto);
    User singup(SingupDto singupDto);
}
