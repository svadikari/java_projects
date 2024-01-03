package com.shyam.orders.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @Column(name = "STYLE_NUMBER")
    private String styleNumber;
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SIZE")
    private String size;

    @Column(name = "COLOR")
    private String color;
    @Column(name = "PRICE")
    private BigDecimal price;

}
