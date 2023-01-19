package com.cab.fab5cabbooking.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy= InheritanceType.JOINED)
@Table(name="usersTable")
public class Signup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotNull
    @Pattern(regexp="[a-zA-Z0-9]{4,15}", message = "Username must be between 4 to 15 characters & should only contain lowercase characters.")
    private String userName;

    @NotNull
    @Pattern(regexp="[a-zA-Z0-9]{5,15}",message="Password should be between 5 to 15 characters and alphanumeric with both Uppercase and lowercase characters.")
    private String password;

    @NotNull
    @Pattern(regexp="[0-9]{10}", message = "Mobile number should have 10 digits")
    private String mobileNo;

    @Email
    @NotNull
    private String email;

}
