package com.qikserve.checkout.controller;

import com.qikserve.checkout.model.entities.cart.Cart;
import com.qikserve.checkout.model.entities.cart.CartItem;
import com.qikserve.checkout.model.entities.cart.CartSummary;
import com.qikserve.checkout.service.cart.ICartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final ICartService cartService;

    @PostMapping
    public ResponseEntity<Cart> createCart() {
        Cart response = this.cartService.createCart();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId) {
        Cart response = this.cartService.getCartById(cartId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> response = this.cartService.getAllCarts();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{cartId}/item")
    public ResponseEntity<CartItem> addCartItem(@PathVariable Long cartId, @Valid @RequestBody CartItem cartItem){
        this.cartService.addItem(cartId, cartItem);
        return ResponseEntity.ok(cartItem);
    }


    @DeleteMapping("/{cartId}/item/{cartItemId}")
    public ResponseEntity<String> removeCartItem(@PathVariable Long cartId, @PathVariable Long cartItemId){
        this.cartService.removeItem(cartId, cartItemId);

        return ResponseEntity.ok("Item with id " + cartItemId + " removed");
    }

    @PatchMapping("{cartId}/clear")
    public ResponseEntity<CartItem> clearCart(@PathVariable Long cartId) {
        this.cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/{cartId}/cancel")
    public ResponseEntity<Void> cancelCart(@PathVariable Long cartId) {
        this.cartService.cancel(cartId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{cartId}")
    public ResponseEntity<CartSummary> checkout(@PathVariable Long cartId) {
        CartSummary response = this.cartService.checkout(cartId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{cartId}/summary")
    public ResponseEntity<CartSummary> cartSummary(@PathVariable Long cartId) {
        CartSummary response = this.cartService.getCartSummaryByCartId(cartId);
        return ResponseEntity.ok(response);
    }


}