package com.qikserve.checkout.exception.cart.base;

import com.qikserve.checkout.exception.BaseException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Getter(onMethod_ = {@Override})
public abstract class CartItemException extends BaseException implements ICartItemId {
    private final Long cartItemId;

    protected CartItemException(HttpStatus status, String messageCode, Long cartItemId) {
        super(status, messageCode);
        this.cartItemId = cartItemId;
    }

    @Override
    protected Object[] getArgs() {
        return new Object[]{cartItemId};
    }
}