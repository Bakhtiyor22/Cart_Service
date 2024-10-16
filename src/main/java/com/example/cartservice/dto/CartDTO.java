package com.example.cartservice.dto;

public class CartDTO {
    private Long id;
    private Long productId;
    private Integer quantity;

    // No-args constructor
    public CartDTO() {}

    // Constructor with parameters
    public CartDTO(Long id, Long productId, Integer quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }


    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}