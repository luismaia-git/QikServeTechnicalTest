package com.qikserve.checkout.model.dto.promotion.implementations;

import com.qikserve.checkout.model.dto.promotion.base.AbstractPromotion;
import com.qikserve.checkout.model.dto.promotion.base.PromotionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class FlatPercentPromotion extends AbstractPromotion {
    private final String id;
    private final PromotionType type = PromotionType.FLAT_PERCENT;
    private final int amount;

    @Override
    public BigDecimal applyPromotion(int quantity, int pricePerUnit) {
        BigDecimal originPricePerUnit = BigDecimal.valueOf(pricePerUnit).divide(BigDecimal.valueOf(100));
        BigDecimal totalPrice = originPricePerUnit.multiply(BigDecimal.valueOf(quantity));
        return totalPrice.multiply(BigDecimal.valueOf(amount).divide(BigDecimal.valueOf(100)));
    }
}
