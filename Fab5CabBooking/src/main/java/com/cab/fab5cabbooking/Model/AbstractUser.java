package com.cab.fab5cabbooking.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AbstractUser {
	

	
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

}
