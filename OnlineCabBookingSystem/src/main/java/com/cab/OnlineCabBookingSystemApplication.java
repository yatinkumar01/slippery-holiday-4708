package com.cab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OnlineCabBookingSystemApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(OnlineCabBookingSystemApplication.class, args);
    }

}
