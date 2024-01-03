package com.shyam.orders.repository;

import com.shyam.orders.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController(path = "/orders")
public interface OrderRepository extends MongoRepository<Order, String> {
}
