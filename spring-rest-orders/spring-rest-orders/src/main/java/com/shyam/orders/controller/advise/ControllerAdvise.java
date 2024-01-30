package com.shyam.orders.controller.advise;

import com.shyam.orders.exception.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorCode> handleException(InvalidInputException exp) {
        return new ResponseEntity<>(new ErrorCode(HttpStatus.BAD_REQUEST.name(), exp.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    record ErrorCode(String code, String description) {
    }
}
