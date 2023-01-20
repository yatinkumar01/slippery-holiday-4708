package com.cab.fab5cabbooking.Exceptions;

public class TripNotFoundException extends RuntimeException{
    public TripNotFoundException() {
    }

    public TripNotFoundException(String message) {
        super(message);
    }
}
