package com.qikserve.checkout.entities.promotion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FlatPercentPromotion implements IPromotion {
    private final String id;
    private final PromotionType type = PromotionType.FLAT_PERCENT;
    private final int amount;

    @Override
    public Integer applyPromotion(int quantity, int pricePerUnit) {
        int totalCost = quantity * pricePerUnit;
        return (totalCost * amount) / 100;
    }
}
