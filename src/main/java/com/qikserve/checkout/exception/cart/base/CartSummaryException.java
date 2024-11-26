package com.qikserve.checkout.exception.cart.base;

import com.qikserve.checkout.exception.BaseException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Getter(onMethod_ = {@Override})
public abstract class CartSummaryException extends BaseException implements ICartSummaryId {
    private final Long cartSummaryId;

    protected CartSummaryException(HttpStatus status, String messageCode, Long cartSummaryId) {
        super(status, messageCode);
        this.cartSummaryId = cartSummaryId;
    }

    @Override
    protected Object[] getArgs() {
        return new Object[]{cartSummaryId};
    }
}