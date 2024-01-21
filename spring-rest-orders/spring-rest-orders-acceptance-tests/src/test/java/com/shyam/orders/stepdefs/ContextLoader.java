package com.shyam.orders.stepdefs;

import com.shyam.orders.OrdersApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = {OrdersApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        value = "server.port=8081")
public class ContextLoader {
}
