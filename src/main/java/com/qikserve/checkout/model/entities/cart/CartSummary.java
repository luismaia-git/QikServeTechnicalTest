package com.qikserve.checkout.model.entities.cart;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
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

    @ElementCollection
    private List<String> items;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "totalPrice", column = @Column(name = "total_price")),
        @AttributeOverride(name = "totalSavings", column = @Column(name = "total_savings")),
        @AttributeOverride(name = "totalPriceFinal", column = @Column(name = "total_price_final"))
    })
    private Savings savings;

    @Column(name = "checkout_date", nullable = false)
    private LocalDateTime checkoutDate;
}
