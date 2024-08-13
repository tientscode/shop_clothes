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
    private int customerId;
    private int productId;
    private int quantity;
    private Long price;
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
