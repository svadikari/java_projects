package com.shyam.orders.service.impl;

import com.shyam.orders.domain.OrderDto;
import com.shyam.orders.entities.Order;
import com.shyam.orders.exception.InvalidInputException;
import com.shyam.orders.mapper.OrderMapper;
import com.shyam.orders.repository.OrderRepository;
import com.shyam.orders.service.OrderService;
import com.shyam.orders.validator.OrderValidatorState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.shyam.orders.validator.OrderValidator.isValidPrice;
import static com.shyam.orders.validator.OrderValidator.isValidStyle;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    @Override
    public List<OrderDto> fetchOrders() {
        List<Order> orders = orderRepository.findAll();
        log.info("No.of orders from DB: "+orders.size());
        return orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto){
        validateInput(orderDto);
        return orderMapper.toDto(orderRepository.save(orderMapper.toEntity(orderDto)));
    }

    private void validateInput(OrderDto orderDto){
        OrderValidatorState result = isValidStyle().and(isValidPrice()).apply(orderDto);
        if (result != OrderValidatorState.VALID) {
            throw new InvalidInputException(result.name());
        }
    }

    @Override
    public OrderDto fetchOrder(String orderNumber) {
        return orderMapper.toDto(orderRepository.findByOrderNumber(orderNumber).stream().findFirst().orElse(null));
    }
}
