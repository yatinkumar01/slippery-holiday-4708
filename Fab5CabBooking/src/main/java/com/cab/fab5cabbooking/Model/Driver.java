package com.cab.fab5cabbooking.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer driverId;
    private Float rating;
    private String licenceNo;
    @Embedded
    private AbstractUser user;
    @OneToOne(cascade = CascadeType.ALL)
    private Cab cab;

}
