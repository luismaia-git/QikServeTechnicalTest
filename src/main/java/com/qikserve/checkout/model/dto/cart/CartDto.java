package com.qikserve.checkout.model.dto.cart;

import com.qikserve.checkout.model.entities.cart.CartItem;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Builder
@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private List<CartItem> items;
}
