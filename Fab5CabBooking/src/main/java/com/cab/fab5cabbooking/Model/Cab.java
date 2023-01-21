package com.cab.fab5cabbooking.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cabId;
    @NotNull
    private CabType cabtype;
    private double perKmRate;
    private Integer capacity;
    private Boolean cabAvailable = true;
    @NotNull
    private String registrationNumber;

}

