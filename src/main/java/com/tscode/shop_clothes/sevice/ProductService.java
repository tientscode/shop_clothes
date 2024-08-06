package com.tscode.shop_clothes.sevice;

import com.tscode.shop_clothes.entity.Products;
import com.tscode.shop_clothes.model.dto.ProductDto;

public interface ProductService  {
    Products save(ProductDto productDto);
}
