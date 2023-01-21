package com.cab.fab5cabbooking.Exceptions;

public class DriverNotFoundException extends RuntimeException {
    public DriverNotFoundException() {
    }

    public DriverNotFoundException(String message) {
        super(message);
    }
}
