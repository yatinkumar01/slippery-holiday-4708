package com.cab.fab5cabbooking.Exceptions;

import lombok.Data;

import java.time.LocalDateTime;
public class MyErrorDetails {

    private LocalDateTime time_stamp;
    private String message;
    private String details;

    public MyErrorDetails(LocalDateTime time_stamp, String message, String details) {
        this.time_stamp = time_stamp;
        this.message = message;
        this.details = details;
    }

    public MyErrorDetails() {
    }

    public LocalDateTime getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(LocalDateTime time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "MyErrorDetails{" +
                "time_stamp=" + time_stamp +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
