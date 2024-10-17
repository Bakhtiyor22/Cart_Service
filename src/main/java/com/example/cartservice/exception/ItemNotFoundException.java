package com.example.cartservice.exception;

public class ItemNotFoundException extends RuntimeException {
  public ItemNotFoundException(Long productId) {
    super("Item not found with product ID: " + productId);
  }
}