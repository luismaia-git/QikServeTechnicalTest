package com.qikserve.checkout.model.entities.promotion.base;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "promotion")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractPromotion implements IPromotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private PromotionType type;

    @Override
    public abstract BigDecimal applyPromotion(int quantity, int pricePerUnit);
}