package com.shyam.orders.repository;

import com.shyam.orders.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer getCustomerById(String id);

    Customer getCustomerByEmail(String email);
}
