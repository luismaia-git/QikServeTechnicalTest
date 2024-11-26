package com.qikserve.checkout.exception.cart.notfound;

import com.qikserve.checkout.exception.cart.base.CartException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartNotFoundException extends CartException {

    public CartNotFoundException(Long cartId) {
        super(HttpStatus.NOT_FOUND, "error.cartIdNotFound", cartId);
    }

}
