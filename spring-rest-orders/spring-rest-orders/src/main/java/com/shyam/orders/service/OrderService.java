package com.shyam.orders.service;

import com.shyam.orders.domain.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> fetchOrders();

    OrderDto createOrder(OrderDto orderDto) throws Exception;

    OrderDto fetchOrder(String orderNumber);
}
