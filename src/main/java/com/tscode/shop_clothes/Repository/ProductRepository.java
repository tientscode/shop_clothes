package com.tscode.shop_clothes.Repository;


import com.tscode.shop_clothes.entity.Categories;
import com.tscode.shop_clothes.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public interface ProductRepository extends JpaRepository<Products, Long> {


    //    lấy 5 sản phẩm mới nhất
    default List<Products> getLatestProducts(List<Products> products) {
        return products.stream()
                .sorted(Comparator.comparing(Products::getCreatedAt).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    default List<Products> getallProducts(List<Products> products) {
        return products.stream()
                .sorted(Comparator.comparing(Products::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }


    @Query("SELECT p FROM Products p JOIN p.categories c WHERE c = :category")
    List<Products> findByCategory(@Param("category") Categories category);
    Products findByName(String name);


}
