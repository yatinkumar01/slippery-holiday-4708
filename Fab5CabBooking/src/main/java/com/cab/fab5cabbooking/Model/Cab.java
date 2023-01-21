package com.cab.fab5cabbooking.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private double perKmRate;
//    private Integer capacity;
    private Integer sittingCapacity;

    private Boolean cabAvailable = true;
    @NotNull
    private String registrationNumber;

    @JsonIgnore
    @OneToOne(mappedBy = "cab", cascade = CascadeType.ALL)
    private Driver driver;

}

