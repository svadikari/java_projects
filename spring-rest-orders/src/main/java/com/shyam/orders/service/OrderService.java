package com.shyam.orders.service;

import com.shyam.orders.domain.OrderDto;
import java.util.List;

public interface OrderService {
    List<OrderDto> fetchOrders();

    void createOrder(OrderDto orderDto);
}
