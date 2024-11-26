package com.qikserve.checkout.exception.cart;

import com.qikserve.checkout.exception.cart.base.CartException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CartAlreadyCheckedOutException extends CartException {
    public CartAlreadyCheckedOutException(Long cartId) {
        super(HttpStatus.BAD_REQUEST, "error.cartAlreadyCheckedOut", cartId);
    }
}
