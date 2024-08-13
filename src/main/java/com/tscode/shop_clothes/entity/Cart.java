package com.tscode.shop_clothes.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long customerId;
    private long productId;
    private int quantity;
    private double price;
    private String size;
    private String color;
    @Column(nullable = false)
    private LocalDateTime createdAt;


}
