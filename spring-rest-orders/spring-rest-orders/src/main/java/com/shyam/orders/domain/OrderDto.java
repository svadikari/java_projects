package com.shyam.orders.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    String id;
    String orderNumber;
    String styleNumber;
    Integer units;
    BigDecimal price;
}
