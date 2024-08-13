package com.tscode.shop_clothes.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String code;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String TargetCustomer;
    Integer quantity;
    String image;
    Double price;
    boolean active;
    @Column(nullable = false)
    String description;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Categories> categories;

    public String toSlug(String name) {
        return name.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("-$", "");
    }

}