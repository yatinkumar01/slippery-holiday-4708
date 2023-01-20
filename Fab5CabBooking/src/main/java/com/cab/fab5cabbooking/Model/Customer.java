package com.cab.fab5cabbooking.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @Embedded
    private AbstractUser user;
    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private TripBooking tripBooking;

}
