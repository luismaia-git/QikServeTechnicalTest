package com.qikserve.checkout.service.cart;


import com.qikserve.checkout.exception.cart.CartItemNotFoundException;
import com.qikserve.checkout.exception.cart.CartNotFoundException;
import com.qikserve.checkout.model.dto.promotion.base.AbstractPromotion;
import com.qikserve.checkout.model.entities.cart.*;
import com.qikserve.checkout.model.dto.product.Product;

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
public class CartServiceImpl implements ICartService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final CartSummaryService cartSummaryService;

    public Cart createCart() {
        Cart cart = Cart.builder()
                .status(CartStatus.OPEN)
                .build();

        return cartRepository.save(cart);
    }

    public void addItem(Long cartId, @Valid CartItem cartItem) {
        Cart cart = this.getCartById(cartId);

        if(!cart.getStatus().equals(CartStatus.OPEN)){
            throw new IllegalArgumentException("Cart with id " + cartId + " is not open");
        }
        cartItemRepository.save(cartItem);
    }

    @Override
    public void removeItem(Long cartId, Long cartItemId) {
        Cart cart = this.getCartById(cartId);

        if(!cart.getStatus().equals(CartStatus.OPEN)){
            throw new IllegalArgumentException("Cart with id " + cartId + " is not open");
        }

        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(CartItemNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }

    public CartSummary checkout(Long cartId) {

        Cart cartFinded = this.getCartById(cartId);

        if(cartFinded.getStatus().equals(CartStatus.CHECKOUT)){
            throw new IllegalArgumentException("Cart with id " + cartId + " is already checked out");
        }

        updateStatusCart(cartId, CartStatus.CHECKOUT);

        Savings cartSavings = calcCartSavings(cartId);

        CartSummary cartSummary = CartSummary.builder()
                .cartId(cartId)
                .savings(cartSavings)
                .checkoutDate(LocalDateTime.now())
                .build();

        return cartSummaryService.createCartSummary(cartSummary);
    }

    public Savings calcCartSavings(Long cartId){
        List<CartItem> cartItems = this.getCartById(cartId).getItems();

        BigDecimal totalCost = BigDecimal.valueOf(0);
        BigDecimal totalSavings = BigDecimal.valueOf(0);

        for (CartItem item : cartItems) {

            String productId = item.getProductId();

            Product product = productRepository.findById(productId);
            int quantity = item.getQuantity();
            int pricePerUnit = product.getPrice();
            int itemCost = quantity * pricePerUnit;
            BigDecimal itemSavings = BigDecimal.valueOf(0);

            for (AbstractPromotion promotion : product.getPromotions()) {
                itemSavings = itemSavings.add(promotion.applyPromotion(quantity, pricePerUnit));
            }

            totalCost = totalCost.add(BigDecimal.valueOf(itemCost).subtract(itemSavings));
            totalSavings = totalSavings.add(itemSavings);
        }

        return Savings.builder().totalPrice(totalCost).totalSavings(totalSavings).totalPriceFinal(totalCost.subtract(totalSavings)).build();
    }


    @Override
    public void clearCart(Long cartId) {
        Cart cart = this.getCartById(cartId);
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public CartSummary getCartSummaryByCartId(Long cartId) {
        return cartSummaryService.getCartSummaryByCartId(cartId);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartById (Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
    }

    public void updateStatusCart(Long cartId, CartStatus status){
        Cart cart = this.getCartById(cartId);
        cart.setStatus(status);
        cartRepository.save(cart);
    }

    public void cancel(Long cartId){
        updateStatusCart(cartId, CartStatus.CANCELLED);
    }

}