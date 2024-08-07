package com.tscode.shop_clothes.Repository;

import com.tscode.shop_clothes.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories, Long> {

}
