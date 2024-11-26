package com.qikserve.checkout.service.cart.item;

import com.qikserve.checkout.exception.cart.notfound.CartItemNotFoundException;
import com.qikserve.checkout.model.entities.cart.*;
import com.qikserve.checkout.repository.cart.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements ICartItemService {

    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        CartItem cartItemBuilded = CartItem.builder()
                .quantity(cartItem.getQuantity())
                .build();
        return cartItemRepository.save(cartItemBuilded);
    }

    @Override
    public void removeCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()-> new  CartItemNotFoundException(cartItemId));
        cartItemRepository.delete(cartItem);
    }

    @Override
    public CartItem getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId).orElseThrow(()-> new CartItemNotFoundException(cartItemId));
    }

    @Override
    public void updateQuantityCartItem(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()-> new  CartItemNotFoundException(cartItemId));
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }
}