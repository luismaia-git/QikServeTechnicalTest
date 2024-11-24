package com.qikserve.checkout.model.entities.cart;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Builder
@Entity
@Table(name = "carts_summary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cart_id", nullable = false)
    private Long cartId;

    @Transient
    private List<CartItem> items;

    @Column(nullable = false)
    private BigDecimal totalCost;

    @Column(nullable = false)
    private BigDecimal savings;

    @Transient
    private BigDecimal totalPayable;
}
