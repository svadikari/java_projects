package com.shyam.orders.repository;

import com.shyam.orders.domain.OrderDto;
import com.shyam.orders.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Collection;

@RepositoryRestController(path = "/orders")
public interface OrderRepository extends MongoRepository<Order, String> {
    Collection<Order> findByOrderNumber(String orderNumber);
}
