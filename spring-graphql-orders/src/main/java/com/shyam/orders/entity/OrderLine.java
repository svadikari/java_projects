package com.shyam.orders.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ORDER_LINES")
public class OrderLine {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_LINE_ID")
    private long id;
    @Column(name = "QUANTITY")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @OneToOne
    @JoinColumn(name = "STYLE_NUMBER", nullable = false, updatable = false)
    private Product product;
}
