package com.qikserve.checkout.repository.cart;

import com.qikserve.checkout.model.entities.cart.CartSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartSummaryRepository extends JpaRepository<CartSummary, Long> {
}
