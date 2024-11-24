package com.qikserve.checkout.model.dto.product;

import com.qikserve.checkout.model.dto.promotion.base.AbstractPromotion;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Builder
@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AbstractPromotion> promotions;

}
