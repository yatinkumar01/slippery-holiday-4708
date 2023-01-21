package com.cab.fab5cabbooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cab.fab5cabbooking.Exceptions.AdminException;
import com.cab.fab5cabbooking.Model.AbstractUser;
import com.cab.fab5cabbooking.Model.Admin;

public interface AdminRepository extends JpaRepository<Admin , Integer> {

	public Admin findByUsername(String username)throws AdminException;
	
}
