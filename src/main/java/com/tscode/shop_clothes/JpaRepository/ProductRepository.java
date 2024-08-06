package com.tscode.shop_clothes.JpaRepository;


import com.tscode.shop_clothes.entity.Categories;
import com.tscode.shop_clothes.entity.Products;
import com.tscode.shop_clothes.model.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
public interface ProductRepository extends  JpaRepository<Products,Long> {
//    Products save(ProductDto productDto);

}
