package com.qikserve.checkout.entities.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CartSummary {
    private final List<CartItem> items;
    private final int totalCost;
    private final int savings;
}
