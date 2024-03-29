package com.shyam.orders.service.impl;

import com.shyam.orders.domain.OrderDto;
import com.shyam.orders.entities.Order;
import com.shyam.orders.exception.InvalidInputException;
import com.shyam.orders.mapper.OrderMapper;
import com.shyam.orders.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;
    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void fetchOrders() {
        when(orderRepository.findAll()).thenReturn(prepareOrders());
        List<OrderDto> orderDtos = orderService.fetchOrders();
        verify(orderRepository, times(1)).findAll();
        assertEquals(4, orderDtos.size());
    }

    @Test
    void saveOrderSuccessfully() throws Exception {
        when(orderRepository.save(any())).thenReturn(Order.builder().id(UUID.randomUUID().toString()).orderNumber("O123").styleNumber("1234").units(5).price(BigDecimal.TEN).build());
        orderService.createOrder(OrderDto.builder()
                .orderNumber("O123")
                .styleNumber("123456")
                .units(5)
                .price(BigDecimal.TEN)
                .build());
        verify(orderRepository, times(1)).save(any());
    }


    @Test
    void shouldThrowInvalidStyleError() {
        assertEquals("INVALID_STYLE", assertThrows(InvalidInputException.class,
                () -> {
                    orderService.createOrder(OrderDto.builder()
                            .orderNumber("O123")
                            .styleNumber("123")
                            .units(5)
                            .price(BigDecimal.TEN)
                            .build());
                }).getMessage());
    }

    @Test
    void shouldThrowInvalidPriceError() {
        assertEquals("INVALID_PRICE", assertThrows(InvalidInputException.class,
                () -> {
                    orderService.createOrder(OrderDto.builder()
                            .orderNumber("O123")
                            .styleNumber("123134")
                            .units(5)
                            .price(BigDecimal.ZERO)
                            .build());
                }).getMessage());
    }


    private List<Order> prepareOrders() {
        return Arrays.asList(
                Order.builder().orderNumber("O111").id(UUID.randomUUID().toString()).build(),
                Order.builder().orderNumber("O222").id(UUID.randomUUID().toString()).build(),
                Order.builder().orderNumber("O333").id(UUID.randomUUID().toString()).build(),
                Order.builder().orderNumber("O444").id(UUID.randomUUID().toString()).build()

        );

    }
}