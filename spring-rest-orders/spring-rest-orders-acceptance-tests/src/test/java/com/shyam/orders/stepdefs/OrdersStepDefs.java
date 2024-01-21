package com.shyam.orders.stepdefs;

import com.shyam.orders.domain.OrderDto;
import io.cucumber.core.internal.com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java8.En;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.util.ResourceUtils;

public class OrdersStepDefs implements En {

    private ObjectMapper objectMapper = new ObjectMapper();

    private String SERVICE_URL = "http://localhost:8081/shyam";

    // Constructor based
    public OrdersStepDefs() {
        Given("Order Service ready to create orders", () -> {
            System.out.println("Order Service ready to create orders");
        });
        When("^Order request submitted with (.*) payload$", (String requestFileName) -> {
            OrderDto orderDto =
                    objectMapper.readValue(ResourceUtils.getFile("classpath:requests/".concat(requestFileName)),
                            new TypeReference<OrderDto>() {
                            });
            System.out.println("request submitting to service: " + (orderDto));
            Response response = RestAssured.given().baseUri(SERVICE_URL).accept("application/json").contentType(
                    ContentType.JSON).body(orderDto).post("/orders").thenReturn();
            System.out.println("Response from service: " + response.getBody().as(OrderDto.class));

        });
        Then("^Order Service response with (.*)$", (Integer status) -> {
            System.out.println("Service return with status: ".concat(String.valueOf(status)));
        });
    }

    // Annotation based
    @Given("Order Service is ALIVE")
    public void orderServiceIsUp() {
        System.out.println("SERVICE IS UP");
    }

    @When("^Fetch Order call initiated for (.*)$")
    public void fetch_order_call_initiated_for(String orderNumber) {
        System.out.println("Fetching Order for " + orderNumber);
    }

    @Then("^Service return (.*) with (.*)$")
    public void service_return_for(String status, String orderNumber) {
        System.out.println("Order " + orderNumber + "return with status: " + status);
    }
}
