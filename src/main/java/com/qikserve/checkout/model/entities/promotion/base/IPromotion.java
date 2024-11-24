package com.qikserve.checkout.model.entities.promotion.base;
import java.math.BigDecimal;

public interface IPromotion {
    String getId();
    PromotionType getType();
    BigDecimal applyPromotion(int quantity, int pricePerUnit);
}
