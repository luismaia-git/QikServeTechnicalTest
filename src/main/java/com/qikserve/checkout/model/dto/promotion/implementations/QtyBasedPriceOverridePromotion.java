package com.qikserve.checkout.model.dto.promotion.implementations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qikserve.checkout.model.dto.promotion.base.AbstractPromotion;
import com.qikserve.checkout.model.dto.promotion.base.PromotionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class QtyBasedPriceOverridePromotion extends AbstractPromotion {
    private final String id;
    private final PromotionType type = PromotionType.QTY_BASED_PRICE_OVERRIDE;

    @JsonProperty("required_qty")
    private int requiredQty;

    private int price;

    @Override
    public BigDecimal applyPromotion(int quantity, int pricePerUnit) {

        BigDecimal originPricePerUnit = BigDecimal.valueOf(pricePerUnit).divide(BigDecimal.valueOf(100));
        BigDecimal promotionalPrice = BigDecimal.valueOf(price).divide(BigDecimal.valueOf(100));

        int sets = quantity / requiredQty;
        int remaining = quantity % requiredQty;

        BigDecimal priceWithDiscount = promotionalPrice.multiply(BigDecimal.valueOf(sets))
                .add(originPricePerUnit.multiply(BigDecimal.valueOf(remaining)));
        BigDecimal originalPriceTotal = originPricePerUnit.multiply(BigDecimal.valueOf(quantity));

        return originalPriceTotal.subtract(priceWithDiscount);
    }
}
