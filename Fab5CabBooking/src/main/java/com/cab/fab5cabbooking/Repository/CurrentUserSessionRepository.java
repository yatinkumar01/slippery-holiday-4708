package com.cab.fab5cabbooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cab.fab5cabbooking.Model.CurrentSessionUser;

public interface CurrentUserSessionRepository  extends JpaRepository<CurrentSessionUser, Integer>{

	
	public CurrentSessionUser findByUuid(String key);
}
