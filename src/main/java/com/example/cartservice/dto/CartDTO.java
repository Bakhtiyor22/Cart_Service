package com.example.cartservice.dto;

import java.util.List;

public class CartDTO {
    private Long id;
    private Long userId;
    private List<Long> productIds; // Assuming you want to keep track of product IDs directly

    // Constructor
    public CartDTO(Long id, Long userId, List<Long> productIds) {
        this.id = id;
        this.userId = userId;
        this.productIds = productIds;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
