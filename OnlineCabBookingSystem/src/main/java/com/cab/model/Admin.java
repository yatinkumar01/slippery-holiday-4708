package com.cab.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

	@Id
	@GeneratedValue ( strategy =  GenerationType.AUTO)
	private Integer id;
	private String name;
	private String password;
}
