package com.qikserve.checkout.service.cart.summary;

import com.qikserve.checkout.exception.cart.CartSummaryNotFoundException;
import com.qikserve.checkout.model.entities.cart.CartSummary;
import com.qikserve.checkout.repository.cart.CartSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartSummaryService implements ICartSummaryService{

    private final CartSummaryRepository cartSummaryRepository;

    @Override
    public CartSummary getCartSummaryById(Long cartSummaryId) {
        return cartSummaryRepository.findById(cartSummaryId).orElseThrow(CartSummaryNotFoundException::new);
    }

    @Override
    public CartSummary getCartSummaryByCartId(Long cartId) {
        return cartSummaryRepository.findByCartId(cartId).orElseThrow(CartSummaryNotFoundException::new);
    }

    @Override
    public CartSummary createCartSummary(CartSummary cartSummary) {
        return cartSummaryRepository.save(cartSummary);
    }
}
