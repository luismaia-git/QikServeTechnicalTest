package com.qikserve.checkout.service.checkout;


import com.qikserve.checkout.model.dto.cart.CartDto;
import com.qikserve.checkout.model.entities.cart.CartItem;
import com.qikserve.checkout.model.entities.cart.CartSummary;
import com.qikserve.checkout.model.entities.product.Product;
import com.qikserve.checkout.model.entities.promotion.base.IPromotion;

import com.qikserve.checkout.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CheckoutServiceImpl implements ICheckoutService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public CartSummary calculateCartSummary(CartDto cartDto) {
        List<CartItem> cartItems = cartDto.getItems();
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

            for (IPromotion promotion : product.getPromotions()) {
                itemSavings = itemSavings.add(promotion.applyPromotion(quantity, pricePerUnit));
            }

            totalCost = totalCost.add(BigDecimal.valueOf(itemCost).subtract(itemSavings));
            totalSavings = totalSavings.add(itemSavings);

        }
        //revisar
        return new CartSummary(cartDto.getId()+1, cartDto.getId(), cartItems, totalCost, totalSavings, totalSavings.add(totalCost));
    }

    public Product getProductById(String productId){
        return productRepository.findById(productId);
    }


}