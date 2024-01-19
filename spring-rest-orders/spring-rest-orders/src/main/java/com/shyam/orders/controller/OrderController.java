package com.shyam.orders.controller;

import com.shyam.orders.domain.OrderDto;
import com.shyam.orders.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.util.NamedFeature;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class OrderController {

    private OrderService orderService;
    private FeatureManager featureManager;

    @GetMapping
    public List<OrderDto> orders() {
        return orderService.fetchOrders();
    }

    @GetMapping("/{orderNumber}")
    public OrderDto order(@PathVariable String orderNumber) {
        return orderService.fetchOrder(orderNumber);
    }

    @PostMapping
    public ResponseEntity<String> orders(@RequestBody @Validated OrderDto orderDto) {
        String message = "Order Create Feature Not enabled!";
        HttpStatus httpStatus = HttpStatus.NOT_IMPLEMENTED;
        if(featureManager.isActive(new NamedFeature("CREATE_ORDER"))){
            orderService.createOrder(orderDto);
            message = "Successfully Created!";
            httpStatus = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(message, httpStatus);
    }
}
