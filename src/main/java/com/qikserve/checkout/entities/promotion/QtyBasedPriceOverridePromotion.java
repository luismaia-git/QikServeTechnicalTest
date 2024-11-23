package com.qikserve.checkout.entities.promotion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QtyBasedPriceOverridePromotion implements IPromotion {
    private final String id;
    private final PromotionType type = PromotionType.QTY_BASED_PRICE_OVERRIDE;
    private final int requiredQty;
    private final int discountedPrice;

    @Override
    public Integer applyPromotion(int quantity, int pricePerUnit) {
        int bundles = quantity / requiredQty;
        int remainingItems = quantity % requiredQty;
        int originalCost = quantity * pricePerUnit;
        int discountedCost = (bundles * discountedPrice) + (remainingItems * pricePerUnit);
        return originalCost - discountedCost;
    }
}
