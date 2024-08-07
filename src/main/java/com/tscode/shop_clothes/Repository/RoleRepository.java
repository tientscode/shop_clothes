package com.tscode.shop_clothes.Repository;

import com.tscode.shop_clothes.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
