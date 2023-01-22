package com.cab.fab5cabbooking.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer tripBookingId;
    private String fromLocation;
    private String toLocation;
    private LocalDate fromDateTime;
    private LocalDate toDateTime;
    private Double distanceInKm;

    @JsonIgnore
    private Double bill;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Customer customer;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Driver driver;


}
