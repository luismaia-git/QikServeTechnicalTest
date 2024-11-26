package com.qikserve.checkout.exception.cart;

import com.qikserve.checkout.exception.NotFoundException;

public class CartNotFoundException extends NotFoundException {

    private static final String DEFAULT_MESSAGE = "Cart not found";

    public CartNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

}
