package com.qikserve.checkout.entities.cart;

import com.qikserve.checkout.entities.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartItem {
    private final Product product;
    private final int quantity;
}