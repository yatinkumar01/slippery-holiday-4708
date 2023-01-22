package com.cab.fab5cabbooking.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @NotNull(message = "Field is empty")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,25}", message = "Username length should be greater than 4")
    private String username;

    @NotNull(message = "Field is empty")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,25}", message = "Username length should be greater than 6")
    private String password;

    private String address;

    @NotNull(message = "Enter your Mobile Number")
    @Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
    private String mobileNumber;

    @NotNull(message = "Field is empty")
    @Email
    private String email;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private Set<TripBooking> tripBookingSet = new HashSet<>();


}
