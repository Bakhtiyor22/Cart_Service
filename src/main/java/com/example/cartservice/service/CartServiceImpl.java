package com.example.cartservice.service;

import com.example.cartservice.dto.CartDTO;
import com.example.cartservice.entity.Cart;
import com.example.cartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    private CartDTO mapToCartDTO(Cart cart) {
        return new CartDTO(cart.getId(), cart.getUserId(), null); // Assuming no items
    }

    private Cart mapToCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setUserId(cartDTO.getUserId());
        return cart;
    }

    @Override
    public List<CartDTO> getAllCarts() {
        return cartRepository.findAll().stream()
                .map(this::mapToCartDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CartDTO getCartById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        return mapToCartDTO(cart);
    }

    @Override
    @Transactional
    public CartDTO createCart(CartDTO cartDTO) {
        Cart cart = mapToCart(cartDTO);
        cart = cartRepository.save(cart);
        return mapToCartDTO(cart);
    }

    @Override
    @Transactional
    public CartDTO updateCart(Long id, CartDTO cartDTO) {
        Cart existingCart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        existingCart.setUserId(cartDTO.getUserId());
        existingCart = cartRepository.save(existingCart);
        return mapToCartDTO(existingCart);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
