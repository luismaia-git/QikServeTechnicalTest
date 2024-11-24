package com.qikserve.checkout.service.checkout;

import com.qikserve.checkout.model.dto.cart.CartDto;
import com.qikserve.checkout.model.entities.cart.CartSummary;
import com.qikserve.checkout.model.entities.product.Product;

public interface ICheckoutService {
    /**
     * @param cartDto the cart to calculate the summary.
     * @return resume of the cart.
     */
    CartSummary calculateCartSummary(CartDto cartDto);

    Product getProductById(String productId);
}
