package com.qikserve.checkout.controller;

import com.qikserve.checkout.model.entities.cart.Cart;
import com.qikserve.checkout.model.entities.cart.CartSummary;
import com.qikserve.checkout.model.dto.product.Product;
import com.qikserve.checkout.service.checkout.CartServiceImpl;
import com.qikserve.checkout.service.checkout.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final ICartService cartService;

    @PostMapping
    public ResponseEntity<Cart> createCart() {
        Cart response = cartService.createCart();
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/{cartId}/cancel")
    public ResponseEntity<Void> cancelCart(@PathVariable Long cartId) {
        cartService.cancel(cartId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cartId}")
    public ResponseEntity<CartSummary> checkout(@PathVariable Long cartId) {
        CartSummary response = cartService.checkout(cartId);
        return ResponseEntity.ok(response);
    }


    //test
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable String productId) {
        Product response = cartService.getProductById(productId);
        return ResponseEntity.ok(response);
    }
}