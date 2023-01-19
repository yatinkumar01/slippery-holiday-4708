package com.cab.model;

import java.time.LocalDateTime;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	private String uuid;
	private LocalDateTime timestamp;
}
