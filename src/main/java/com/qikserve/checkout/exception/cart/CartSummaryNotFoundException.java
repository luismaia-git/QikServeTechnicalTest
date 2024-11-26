package com.qikserve.checkout.exception.cart;

import com.qikserve.checkout.exception.NotFoundException;

public class CartSummaryNotFoundException extends NotFoundException {

    private static final String DEFAULT_MESSAGE = "CartSummary not found";

    public CartSummaryNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

}
