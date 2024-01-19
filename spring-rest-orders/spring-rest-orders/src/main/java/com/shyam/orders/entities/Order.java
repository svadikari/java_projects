package com.shyam.orders.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "orders")
@TypeAlias("Order")
@Data
@Builder
public class Order {
    @Id
    private String id;
    @Indexed(unique = true)
    private String orderNumber;
    private String styleNumber;
    private Integer units;
    private BigDecimal price;
}
