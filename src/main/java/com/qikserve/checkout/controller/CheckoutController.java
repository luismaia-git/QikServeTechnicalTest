package com.qikserve.checkout.controller;

import com.qikserve.checkout.model.dto.cart.CartDto;
import com.qikserve.checkout.model.entities.cart.CartSummary;
import com.qikserve.checkout.service.checkout.ICheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
