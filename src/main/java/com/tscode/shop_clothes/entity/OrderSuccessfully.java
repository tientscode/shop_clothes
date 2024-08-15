package com.tscode.shop_clothes.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "OrderSuccessfully")
public class OrderSuccessfully {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Products product;

    int qty;
    private String size;
    private String color;
    double total;
    String status;
    boolean AdCheck;
    private LocalDateTime createdAt;
}
