package com.qikserve.checkout.exception.cart;

import com.qikserve.checkout.exception.NotFoundException;

public class CartItemNotFoundException extends NotFoundException {

    private static final String DEFAULT_MESSAGE = "CartItem not found";

    public CartItemNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

}
