package com.example.cartservice.service;

import com.example.cartservice.dto.CartDTO;
import com.example.cartservice.dto.CartItemDTO;
import com.example.cartservice.entity.Cart;
import com.example.cartservice.entity.CartItem;
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
        List<CartItemDTO> items = cart.getItems().stream()
                .map(item -> new CartItemDTO(item.getId(), item.getProductId(), item.getQuantity()))
                .collect(Collectors.toList());
        return new CartDTO(cart.getId(), cart.getUserId(), items);
    }

    private Cart mapToCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setUserId(cartDTO.getUserId());
        List<CartItem> items = cartDTO.getItems().stream()
                .map(dto -> {
                    CartItem item = new CartItem(dto.getProductId(), dto.getQuantity());
                    item.setCart(cart); // Set the cart reference
                    return item;
                })
                .collect(Collectors.toList());
        cart.setItems(items);
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
        Cart finalExistingCart = existingCart;
        List<CartItem> items = cartDTO.getItems().stream()
                .map(dto -> {
                    CartItem item = new CartItem(dto.getProductId(), dto.getQuantity());
                    item.setCart(finalExistingCart); // Set the cart reference
                    return item;
                })
                .collect(Collectors.toList());
        existingCart.setItems(items);
        existingCart = cartRepository.save(existingCart);
        return mapToCartDTO(existingCart);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CartDTO addItemToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        CartItem item = new CartItem(productId, quantity);
        item.setCart(cart); // Set the cart reference
        cart.addItem(item);
        cart = cartRepository.save(cart);
        return mapToCartDTO(cart);
    }

    @Override
    @Transactional
    public CartDTO removeItemFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.removeItem(productId);
        cart = cartRepository.save(cart);
        return mapToCartDTO(cart);
    }

    @Override
    @Transactional
    public CartDTO updateItemQuantity(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.updateItemQuantity(productId, quantity);
        cart = cartRepository.save(cart);
        return mapToCartDTO(cart);
    }
}
