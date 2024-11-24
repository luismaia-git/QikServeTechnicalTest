package com.qikserve.checkout.service.checkout;

import com.qikserve.checkout.model.entities.cart.Cart;
import com.qikserve.checkout.model.entities.cart.CartItem;
import com.qikserve.checkout.model.entities.cart.CartStatus;
import com.qikserve.checkout.model.entities.cart.CartSummary;
import com.qikserve.checkout.model.dto.product.Product;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface ICartService {
    /**
     * @param cart the cart to calculate the summary.
     * @return resume of the cart.
     */
    CartSummary checkout(Long cartId);
    Cart createCart();
    CartItem addItem(Long cartId, @Valid CartItem cartItem);
    Cart getCart (Long cartId);
    Cart updateStatusCart(Long cartId, CartStatus status);
    void cancel(Long cartId);


    Product getProductById(String productId);
}
