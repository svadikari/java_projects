package com.shyam.orders.mapper;

import com.shyam.orders.domain.OrderDto;
import com.shyam.orders.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);

    Order toEntity(OrderDto orderDto);
}
