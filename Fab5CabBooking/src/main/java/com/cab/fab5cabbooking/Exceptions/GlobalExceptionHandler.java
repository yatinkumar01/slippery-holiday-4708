package com.cab.fab5cabbooking.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerException.class)
    ResponseEntity<MyErrorDetails> customExceptionHandler(CustomerException customerException, WebRequest webRequest) {
        MyErrorDetails myErrorDetails = new MyErrorDetails(LocalDateTime.now(), customerException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<MyErrorDetails>(myErrorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CabException.class)
    ResponseEntity<MyErrorDetails> cabExceptionHandler(CabException cabException, WebRequest webRequest) {
        MyErrorDetails myErrorDetails = new MyErrorDetails(LocalDateTime.now(), cabException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(myErrorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DriverException.class)
    ResponseEntity<MyErrorDetails> driverExceptionHandler(DriverException driverException, WebRequest webRequest) {
        MyErrorDetails myErrorDetails = new MyErrorDetails(LocalDateTime.now(), driverException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(myErrorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    ResponseEntity<MyErrorDetails> noHandlerExceptionHandler(NoHandlerFoundException customerException, WebRequest webRequest) {
        MyErrorDetails myErrorDetails = new MyErrorDetails(LocalDateTime.now(), customerException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(myErrorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<MyErrorDetails> methodArgumentExceptionHandler(MethodArgumentNotValidException customerException, WebRequest webRequest) {
        MyErrorDetails myErrorDetails = new MyErrorDetails(LocalDateTime.now(), "Validation Error", customerException.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(myErrorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<MyErrorDetails> mainExceptionHandler(Exception customerException, WebRequest webRequest) {
        MyErrorDetails myErrorDetails = new MyErrorDetails(LocalDateTime.now(), customerException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(myErrorDetails, HttpStatus.NOT_FOUND);
    }
}
