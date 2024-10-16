package com.example.cartservice.controller;

import com.example.cartservice.dto.CartDTO;
import com.example.cartservice.dto.CreateCartDTO;
import com.example.cartservice.dto.UpdateCartDTO;
import com.example.cartservice.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long id) {
        CartDTO cart = cartService.getCartById(id);
        return ResponseEntity.ok(cart);
    }

    @PostMapping
    public ResponseEntity<CartDTO> createCart(@Valid @RequestBody CreateCartDTO createCartDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.createCart(createCartDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable Long id, @Valid @RequestBody UpdateCartDTO updateCartDTO) {
        CartDTO updatedCart = cartService.updateCart(id, updateCartDTO);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}
