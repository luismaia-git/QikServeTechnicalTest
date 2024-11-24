package com.qikserve.checkout.service;

import com.qikserve.checkout.entities.cart.CartItem;
import com.qikserve.checkout.entities.cart.CartSummary;

import java.util.List;

public interface ICheckoutService {
    /**
     * @param cartItems List.
     * @return resume of the cart.
     */
    CartSummary calculateCartSummary(List<CartItem> cartItems);
}
