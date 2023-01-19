package com.example.onlinecabbookingsystem.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Login {

    @Id
    @NotNull
    private Integer userId;

    private String userName;

    private String password;

}
