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
<<<<<<< HEAD
    private double perKmRate;
    private Integer capacity;
=======
    private Double perKmRate;
    private Integer getCapacity;
>>>>>>> 6efae28d972da61bfa84b931dd058beed419e2d9
    private Boolean cabAvailable = true;
    @NotNull
    private String registrationNumber;
    private Integer driverId;

}

