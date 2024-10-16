package com.example.cartservice.dto;

import java.math.BigDecimal;

public class CartItemDTO {
    private Long productId;
    private String productName;
    private int quantity;
    private BigDecimal price;

    public CartItemDTO(Long productId, String productName, int quantity, BigDecimal price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

}