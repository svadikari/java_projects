package com.shyam.orders.validator;

import com.shyam.orders.domain.OrderDto;

import java.math.BigDecimal;
import java.util.function.Function;

import static com.shyam.orders.validator.OrderValidatorState.INVALID_PRICE;
import static com.shyam.orders.validator.OrderValidatorState.VALID;
import static com.shyam.orders.validator.OrderValidatorState.INVALID_STYLE;

/**
 * Combinator pattern
 */
public interface OrderValidator extends Function<OrderDto, OrderValidatorState> {
    static OrderValidator isValidStyle() {
        return orderDto -> orderDto.getStyleNumber().length() > 5 ? VALID : INVALID_STYLE;
    }

    static OrderValidator isValidPrice() {
        return orderDto -> orderDto.getPrice().equals(BigDecimal.ZERO) ? INVALID_PRICE : VALID;
    }

    default OrderValidator and (OrderValidator orderValidator) {
        return orderDto -> {
            OrderValidatorState result = this.apply(orderDto);
            return result.equals(VALID)? orderValidator.apply(orderDto) : result;
        };
    }
}
