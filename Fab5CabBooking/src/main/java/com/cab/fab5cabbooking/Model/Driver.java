package com.cab.fab5cabbooking.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer driverId;
    @Max(5)
    private Float rating;
    //    @Max(8)
    @Pattern(regexp = "^[A-Z0-9]+$", message = "licence number should consist Capital letters ranging A-Z and " +
            "numerical ranging 0-9 without any space in between them")
    private String licenceNo;

    //    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Cab cab;

    @JsonIgnore
    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL)
    private Set<TripBooking> tripBookings = new HashSet<>();

}
