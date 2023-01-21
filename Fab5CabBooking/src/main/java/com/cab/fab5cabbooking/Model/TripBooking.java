package com.cab.fab5cabbooking.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TripBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer tripBookingId;
    private String fromLocation;
    private String toLocation;
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;
    private Double distanceInKm;

    @JsonIgnore
    private Double bill;

    @OneToOne(mappedBy = "tripBooking", cascade = CascadeType.ALL)
    @JoinColumn(name = "tripBookingId")
    private Customer customer;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Driver driver;
}
