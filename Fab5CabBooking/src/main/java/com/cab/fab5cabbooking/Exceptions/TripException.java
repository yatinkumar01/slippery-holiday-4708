package com.cab.fab5cabbooking.Exceptions;

public class TripException extends RuntimeException{
    public TripException() {
    }

    public TripException(String message) {
        super(message);
    }
}
