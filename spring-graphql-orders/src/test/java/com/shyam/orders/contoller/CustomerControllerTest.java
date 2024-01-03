package com.shyam.orders.contoller;

import com.shyam.orders.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.event.annotation.AfterTestMethod;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
class CustomerControllerTest {

    @Autowired
    GraphQlTester graphQlTester;

    @Test
    void fetchCustomers() {
        String query = """
                {
                    customers {
                        id
                        firstName
                        lastName
                    }
                }
                """;
        Collection<Customer> customers = graphQlTester.document(query).execute().path("data.customers[*]").entityList(Customer.class).get();
        assertFalse(customers.isEmpty());
        assertTrue(customers.stream().anyMatch(customer -> customer.getFirstName().equalsIgnoreCase("Shyam")));

    }

    @Test
    @AfterTestMethod("fetchCustomers")
    void customerById() {
        String query = """
                {
                  customerById(id: "1234") {
                    id
                    firstName
                    lastName
                    phone
                    email
                    line1
                  }
                }
                """;
        Customer customer = graphQlTester.document(query).execute().path("data.customerById").entity(Customer.class).get();
        assertEquals(customer.getFirstName(), "Shyam");
    }

    @Test
    @AfterTestMethod("customerById")
    void customerByEmail() {
        String query = """
                {
                  customerByEmail(email: "shyam@shyam.com") {
                    id
                    firstName
                    lastName
                    phone
                    email
                    line1
                  }
                }
                """;
        Customer customer = graphQlTester.document(query).execute().path("data.customerByEmail").entity(Customer.class).get();
        assertEquals(customer.getFirstName(), "Shyam");
    }

    @Test
    void addCustomer() {

        String query = """
                mutation{
                  addCustomer(customer:{
                      firstName: "Ram",
                      lastName: "V",
                      phone: "(123)-232-3432",
                      email: "ram@ram.com",
                      line1: "123 Lane",
                      line2: "344",
                      city: "Tracy",
                      state: "CA",
                      zip: "34445"
                      
                  }) {
                    id
                    firstName
                    lastName
                    phone
                    email
                    line1
                  }
                }
                """;
        Customer customer = graphQlTester.document(query).execute().path("data.addCustomer").entity(Customer.class).get();
        assertEquals(customer.getFirstName(), "Ram");
    }
}