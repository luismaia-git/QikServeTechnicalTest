package com.qikserve.checkout.service;


import com.qikserve.checkout.entities.cart.CartItem;
import com.qikserve.checkout.entities.cart.CartSummary;
import com.qikserve.checkout.entities.product.Product;
import com.qikserve.checkout.entities.promotion.IPromotion;

import com.qikserve.checkout.repository.WiremockProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutServiceImpl implements ICheckoutService {

    @Autowired
    private WiremockProductRepository productRepository;

    @Override
    public CartSummary calculateCartSummary(List<CartItem> cartItems) {
        int totalCost = 0;
        int totalSavings = 0;

        for (CartItem item : cartItems) {
            String productId = item.getProductId();
            Product product = productRepository.findById(productId);

            int quantity = item.getQuantity();
            int pricePerUnit = product.getPrice();
            int itemCost = quantity * pricePerUnit;
            int itemSavings = 0;

            for (IPromotion promotion : product.getPromotions()) {
                itemSavings += promotion.applyPromotion(quantity, pricePerUnit);
            }

            totalCost += itemCost - itemSavings;
            totalSavings += itemSavings;

        }
        return new CartSummary(cartItems, totalCost, totalSavings);
    }
}