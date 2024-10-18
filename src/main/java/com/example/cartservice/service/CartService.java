package com.example.cartservice.service;

import com.example.cartservice.dto.CartDTO;
import java.util.List;

public interface CartService {
    List<CartDTO> getAllCarts();
    CartDTO getCartById(Long id);
    CartDTO createCart(CartDTO cartDTO);
    CartDTO updateCart(Long id, CartDTO cartDTO);
    void deleteCart(Long id);
}
