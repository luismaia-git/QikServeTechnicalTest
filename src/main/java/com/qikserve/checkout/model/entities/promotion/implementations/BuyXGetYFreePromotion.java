package com.qikserve.checkout.model.entities.promotion.implementations;

import com.qikserve.checkout.model.entities.promotion.base.AbstractPromotion;
import com.qikserve.checkout.model.entities.promotion.base.PromotionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class BuyXGetYFreePromotion extends AbstractPromotion {
    private final String id;
    private final PromotionType type = PromotionType.BUY_X_GET_Y_FREE;
    private final int requiredQty;
    private final int freeQty;

    @Override
    public BigDecimal applyPromotion(int quantity, int pricePerUnit) {
        BigDecimal originPricePerUnit = BigDecimal.valueOf(pricePerUnit).divide(BigDecimal.valueOf(100));
        int sets = quantity / requiredQty;
        int freeItems = sets * freeQty;
        return originPricePerUnit.multiply(BigDecimal.valueOf(freeItems));
    }

}
