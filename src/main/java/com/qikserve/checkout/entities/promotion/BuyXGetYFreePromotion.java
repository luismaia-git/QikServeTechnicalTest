package com.qikserve.checkout.entities.promotion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BuyXGetYFreePromotion implements IPromotion {
    private final String id;
    private final PromotionType type = PromotionType.BUY_X_GET_Y_FREE;
    private final int requiredQty;
    private final int freeQty;

    @Override
    public Integer applyPromotion(int quantity, int pricePerUnit) {
        int bundles = quantity / (requiredQty + freeQty);
        return bundles * freeQty * pricePerUnit;
    }

}
