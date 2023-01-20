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
        MyErrorDetails object = new MyErrorDetails(LocalDateTime.now(), customerException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<MyErrorDetails>(object, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TripNotFoundException.class)
    ResponseEntity<MyErrorDetails> tripNotFoundExceptionHandler(TripNotFoundException tripNotFoundException, WebRequest webRequest) {
        MyErrorDetails object = new MyErrorDetails(LocalDateTime.now(), tripNotFoundException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<MyErrorDetails>(object, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    ResponseEntity<MyErrorDetails> noHandlerExceptionHandler(NoHandlerFoundException customerException, WebRequest webRequest) {
        MyErrorDetails object = new MyErrorDetails(LocalDateTime.now(), customerException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<MyErrorDetails>(object, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<MyErrorDetails> methodArgumentExceptionHandler(MethodArgumentNotValidException customerException, WebRequest webRequest) {
        MyErrorDetails object = new MyErrorDetails(LocalDateTime.now(), "Validation Error", customerException.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<MyErrorDetails>(object, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<MyErrorDetails> mainExceptionHandler(Exception customerException, WebRequest webRequest) {
        MyErrorDetails object = new MyErrorDetails(LocalDateTime.now(), customerException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<MyErrorDetails>(object, HttpStatus.NOT_FOUND);
    }
}
