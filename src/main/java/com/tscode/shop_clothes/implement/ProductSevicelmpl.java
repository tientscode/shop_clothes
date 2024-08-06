package com.tscode.shop_clothes.implement;

import com.tscode.shop_clothes.JpaRepository.ProductRepository;
import com.tscode.shop_clothes.entity.Products;
import com.tscode.shop_clothes.model.dto.ProductDto;
import com.tscode.shop_clothes.sevice.AuthService;
import com.tscode.shop_clothes.sevice.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ProductSevicelmpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public Products save(ProductDto productDto) {
        Products products = new Products();
        products.setName(productDto.getName());
        products.setCode(productDto.getCode());
        products.setDescription(productDto.getDescription());
        return productRepository.save(products);
    }

}
