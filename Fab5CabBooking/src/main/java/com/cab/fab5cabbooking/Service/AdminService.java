package com.cab.fab5cabbooking.Service;

import org.springframework.stereotype.Service;

import com.cab.fab5cabbooking.Exceptions.AdminException;
import com.cab.fab5cabbooking.Model.AbstractUser;
import com.cab.fab5cabbooking.Model.Admin;

public interface AdminService {

	
    public Admin createUser(Admin admin)throws AdminException;
	
    public Admin updateUser(Admin admin,Integer id)throws AdminException;
	
}
