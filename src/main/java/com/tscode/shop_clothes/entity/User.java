package com.tscode.shop_clothes.entity;


import jakarta.persistence.*;
import lombok.*;
import com.tscode.shop_clothes.entity.Role;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    String email;
    String phone;
    String address;
    Boolean active;
    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;
}
