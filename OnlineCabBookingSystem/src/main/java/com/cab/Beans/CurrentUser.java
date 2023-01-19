package com.cab.Beans;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CurrentUser {

	@Id
	@Column(unique = true)
	private Integer id;
	private String uuid;
	private LocalDateTime timestamp;
}
