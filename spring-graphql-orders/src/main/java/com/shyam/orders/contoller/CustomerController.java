package com.shyam.orders.contoller;

import com.shyam.orders.entity.Customer;
import com.shyam.orders.records.CustomerDto;
import com.shyam.orders.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class CustomerController {

    private CustomerRepository customerRepository;

    @QueryMapping(name = "customers")
    public Collection<Customer> fetchCustomers() {
        return customerRepository.findAll();
    }

    @QueryMapping
    public Customer customerById(@Argument String id) {
        return customerRepository.getCustomerById(id);
    }

    @QueryMapping
    public Customer customerByEmail(@Argument String email) {
        return customerRepository.getCustomerByEmail(email);
    }

    @MutationMapping
    public Customer addCustomer(@Argument(name = "customer") CustomerDto customerDto) {
        Customer customer = new Customer(null, customerDto.firstName(), customerDto.lastName(), customerDto.email(), customerDto.phone(), customerDto.line1(), customerDto.line2(), customerDto.city(), customerDto.state(), customerDto.zip());
        return customerRepository.save(customer);
    }

}
