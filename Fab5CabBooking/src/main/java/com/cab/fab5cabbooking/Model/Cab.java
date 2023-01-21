package com.cab.fab5cabbooking.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private Double perKmRate;
    private Integer getCapacity;
    private Boolean cabAvailable = true;
    @NotNull
    private String registrationNumber;
    private Integer driverId;

}

