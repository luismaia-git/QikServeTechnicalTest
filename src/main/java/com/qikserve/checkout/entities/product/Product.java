package com.qikserve.checkout.entities.product;

import com.qikserve.checkout.entities.promotion.IPromotion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Product {

    private String id;
    private String name;
    private int price;
    private List<IPromotion> promotions;

    public Product(String id, String name, int price, List<IPromotion> promotions) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.promotions = promotions;
    }

}
