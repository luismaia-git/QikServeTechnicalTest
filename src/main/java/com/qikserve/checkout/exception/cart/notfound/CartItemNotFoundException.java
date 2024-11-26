package com.qikserve.checkout.exception.cart.notfound;

import com.qikserve.checkout.exception.cart.base.CartItemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartItemNotFoundException extends CartItemException {

    public CartItemNotFoundException(Long cartItemId) {
        super(HttpStatus.NOT_FOUND, "error.cartItemIdNotFound", cartItemId);
    }

}
