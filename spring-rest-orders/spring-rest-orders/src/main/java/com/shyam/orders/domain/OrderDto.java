package com.shyam.orders.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    String id;
    @NonNull
    String orderNumber;
    String styleNumber;
    @NonNull
    Integer units;
    @NonNull
    BigDecimal price;
}
