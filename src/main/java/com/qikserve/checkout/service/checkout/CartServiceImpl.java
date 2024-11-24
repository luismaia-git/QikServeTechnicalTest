package com.qikserve.checkout.service.checkout;


import com.qikserve.checkout.exception.cart.CartNotFoundException;
import com.qikserve.checkout.model.dto.promotion.base.AbstractPromotion;
import com.qikserve.checkout.model.entities.cart.*;
import com.qikserve.checkout.model.dto.product.Product;
import com.qikserve.checkout.model.dto.promotion.base.IPromotion;

import com.qikserve.checkout.repository.cart.CartItemRepository;
import com.qikserve.checkout.repository.cart.CartRepository;
import com.qikserve.checkout.repository.cart.CartSummaryRepository;
import com.qikserve.checkout.repository.product.ProductRepository;
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
    private final CartSummaryRepository cartSummaryRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public Cart createCart() {
        Cart cart = Cart.builder()
                .status(CartStatus.OPEN)
                .build();

        return cartRepository.save(cart);
    }

    public CartItem addItem(Long cartId, @Valid CartItem cartItem) {
        Cart cart = this.getCart(cartId);

        if(!cart.getStatus().equals(CartStatus.OPEN)){
            throw new IllegalArgumentException("Cart with id " + cartId + " is not open");
        }

        return cartItemRepository.save(cartItem);
    }

    public CartSummary checkout(Long cartId) {
        Cart cart  = updateStatusCart(cartId, CartStatus.CHECKOUT);

        List<CartItem> cartItems = cart.getItems();
        BigDecimal totalCost = BigDecimal.valueOf(0);
        BigDecimal totalSavings = BigDecimal.valueOf(0);
        System.out.println(cartItems.size());

        for (CartItem item : cartItems) {

            String productId = item.getProductId();
            System.out.println(productId);
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

        List<String> itemIds = cart.getItems().stream().map(CartItem::getProductId).toList();

        CartSummary cartSummary = CartSummary.builder()
                .cartId(cartId)
                .items(itemIds)
                .savings(Savings.builder().totalPrice(totalCost).totalSavings(totalSavings).totalPriceFinal(totalCost.subtract(totalSavings)).build())
                .checkoutDate(LocalDateTime.now())
                .build();

        return cartSummaryRepository.save(cartSummary);

    }


    public Cart getCart (Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
    }

    public Cart updateStatusCart(Long cartId, CartStatus status){
        Cart cart = this.getCart(cartId);
        cart.setStatus(status);
        return cartRepository.save(cart);
    }

    public void cancel(Long cartId){
        updateStatusCart(cartId, CartStatus.CANCELLED);
    }

    public Product getProductById(String productId){
        return productRepository.findById(productId);
    }


}