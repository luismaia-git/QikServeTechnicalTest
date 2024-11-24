package com.qikserve.checkout.model.entities.cart;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Embeddable
public class Savings {
    private BigDecimal totalPrice;
    private BigDecimal totalSavings;
    private BigDecimal totalPriceFinal;
}
