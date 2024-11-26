package com.qikserve.checkout.exception.cart.base;

import com.qikserve.checkout.exception.BaseException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Getter(onMethod_ = {@Override})
public abstract class CartException extends BaseException implements ICartId {
    private final Long cartId;

    protected CartException(HttpStatus status, String messageCode, Long cartId) {
        super(status, messageCode);
        this.cartId = cartId;
    }

    @Override
    protected Object[] getArgs() {
        return new Object[]{cartId};
    }
}
