package com.qikserve.checkout.exception.cart.notfound;

import com.qikserve.checkout.exception.cart.base.CartSummaryException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartSummaryNotFoundException extends CartSummaryException {

    public CartSummaryNotFoundException(Long cartSummaryId) {
        super(HttpStatus.NOT_FOUND, "error.cartSummaryIdNotFound", cartSummaryId);
    }

}

