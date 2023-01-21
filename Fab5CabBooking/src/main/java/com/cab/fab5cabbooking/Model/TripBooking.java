package com.cab.fab5cabbooking.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Data
public class TripBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Integer tripBookingId;
    private String fromLocation;
    private String toLocation;
    private LocalDate fromDateTime;
    private LocalDate toDateTime;
    private Double distanceInKm;

    @JsonIgnore
    private Double bill;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Driver driver;


}
