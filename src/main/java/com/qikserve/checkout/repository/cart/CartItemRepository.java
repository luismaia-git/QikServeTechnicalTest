package com.qikserve.checkout.repository.cart;

import com.qikserve.checkout.model.entities.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
