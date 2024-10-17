package com.example.cartservice.exception;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(Long cartId) {
        super("Cart not found with ID: " + cartId);
    }
}