package com.qikserve.checkout.controller;

import com.qikserve.checkout.model.dto.cart.CartDto;
import com.qikserve.checkout.model.entities.cart.CartSummary;
import com.qikserve.checkout.model.entities.product.Product;
import com.qikserve.checkout.service.checkout.ICheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    @Autowired
    private ICheckoutService checkoutService;

    @PostMapping()
    public ResponseEntity<CartSummary> calculateCartSummary(@RequestBody CartDto cartDto) {
        CartSummary response = checkoutService.calculateCartSummary(cartDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> calculateCartSummary(@PathVariable String productId) {
        Product response = checkoutService.getProductById(productId);
        return ResponseEntity.ok(response);
    }
}
