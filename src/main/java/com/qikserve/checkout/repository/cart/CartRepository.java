package com.qikserve.checkout.repository.cart;

import com.qikserve.checkout.model.entities.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
