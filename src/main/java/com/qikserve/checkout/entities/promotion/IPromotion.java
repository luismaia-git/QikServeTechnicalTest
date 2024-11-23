package com.qikserve.checkout.entities.promotion;

public interface IPromotion {
    String getId();
    PromotionType getType();
    Integer applyPromotion(int quantity, int pricePerUnit);
}
