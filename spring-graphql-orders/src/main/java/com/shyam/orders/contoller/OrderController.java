package com.shyam.orders.contoller;

import com.shyam.orders.entity.Order;
import com.shyam.orders.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class OrderController {
    private OrderRepository orderRepository;

    @QueryMapping(name = "orders")
    public Collection<Order> fetchOrders() {
        return orderRepository.findAll();
    }
}
