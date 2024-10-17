package com.example.cartservice.service;

import com.example.cartservice.dto.CartDTO;
import java.util.List;

public interface CartService {
    List<CartDTO> getAllCarts();
    CartDTO getCartById(Long id); // Add this method
    CartDTO createCart(CartDTO cartDTO); // Add this method
    CartDTO updateCart(Long id, CartDTO cartDTO);
    void deleteCart(Long id);
    CartDTO addItemToCart(Long cartId, Long productId, int quantity);
    CartDTO removeItemFromCart(Long cartId, Long productId);
    CartDTO updateItemQuantity(Long cartId, Long productId, int quantity);
}
