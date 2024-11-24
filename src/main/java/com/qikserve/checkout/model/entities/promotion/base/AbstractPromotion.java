package com.qikserve.checkout.model.entities.promotion.base;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.qikserve.checkout.model.entities.promotion.implementations.BuyXGetYFreePromotion;
import com.qikserve.checkout.model.entities.promotion.implementations.FlatPercentPromotion;
import com.qikserve.checkout.model.entities.promotion.implementations.QtyBasedPriceOverridePromotion;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "promotion")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(
        {
            @JsonSubTypes.Type(value = BuyXGetYFreePromotion.class, name = "BUY_X_GET_Y_FREE"),
            @JsonSubTypes.Type(value = FlatPercentPromotion.class, name = "FLAT_PERCENT"),
            @JsonSubTypes.Type(value = QtyBasedPriceOverridePromotion.class, name = "QTY_BASED_PRICE_OVERRIDE"),
        }
)
public abstract class AbstractPromotion implements IPromotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private PromotionType type;

    @Override
    public abstract BigDecimal applyPromotion(int quantity, int pricePerUnit);
}