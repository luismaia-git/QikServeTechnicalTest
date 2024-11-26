package com.qikserve.checkout.service.cart;


import com.qikserve.checkout.exception.cart.CartItemNotFoundException;
import com.qikserve.checkout.exception.cart.CartNotFoundException;
import com.qikserve.checkout.model.dto.product.Product;
import com.qikserve.checkout.model.dto.promotion.base.AbstractPromotion;
import com.qikserve.checkout.model.entities.cart.*;
import com.qikserve.checkout.repository.cart.CartItemRepository;
import com.qikserve.checkout.repository.cart.CartRepository;
import com.qikserve.checkout.repository.product.ProductRepository;
import com.qikserve.checkout.service.cart.summary.CartSummaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements ICartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;


    @Override
    public CartItem createCartItem(CartItem cartItem) {
        CartItem cartItemBuilded = CartItem.builder()
                .quantity(cartItem.getQuantity())
                .build();
        return cartItemRepository.save(cartItemBuilded);
    }

    @Override
    public void removeCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(CartItemNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }

    @Override
    public CartItem getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId).orElseThrow(CartItemNotFoundException::new);
    }

    @Override
    public void updateQuantityCartItem(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(CartItemNotFoundException::new);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }
}