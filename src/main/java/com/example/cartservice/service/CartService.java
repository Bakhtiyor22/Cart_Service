package com.example.cartservice.service;

import com.example.cartservice.entity.Cart;
import com.example.cartservice.repository.CartRepository;
import com.example.cartservice.dto.CartDTO;
import com.example.cartservice.dto.CreateCartDTO;
import com.example.cartservice.dto.UpdateCartDTO;
import com.example.cartservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<CartDTO> getAllCarts() {
        return cartRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public CartDTO getCartById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id: " + id));
        return entityToDto(cart);
    }

    public CartDTO createCart(CreateCartDTO createCartDTO) {
        Cart cart = new Cart();
        cart.setProductId(createCartDTO.getProductId());
        cart.setQuantity(createCartDTO.getQuantity());
        // Set other properties if needed
        Cart savedCart = cartRepository.save(cart);
        return entityToDto(savedCart);
    }

    public CartDTO updateCart(Long id, UpdateCartDTO updateCartDTO) {
        Cart existingCart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id: " + id));

        // Update cart properties based on UpdateCartDTO
        existingCart.setProductId(updateCartDTO.getProductId());
        existingCart.setQuantity(updateCartDTO.getQuantity());
        // Update other properties as needed

        Cart updatedCart = cartRepository.save(existingCart);
        return entityToDto(updatedCart);
    }

    public void deleteCart(Long id) {
        if (!cartRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cart not found with id: " + id);
        }
        cartRepository.deleteById(id);
    }

    // Mapping methods
    private CartDTO entityToDto(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setProductId(cart.getProductId());
        dto.setQuantity(cart.getQuantity());
        // Map other properties as needed
        return dto;
    }

    // Note: dtoToEntity method is removed as it's not used in this service
}
