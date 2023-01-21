package com.cab.fab5cabbooking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.fab5cabbooking.Exceptions.AdminException;
import com.cab.fab5cabbooking.Model.AbstractUser;
import com.cab.fab5cabbooking.Model.Admin;
import com.cab.fab5cabbooking.Model.CurrentSessionUser;
import com.cab.fab5cabbooking.Repository.AdminRepository;
import com.cab.fab5cabbooking.Repository.CurrentUserSessionRepository;
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository repo;
	
	@Autowired
	private CurrentUserSessionRepository srepo;
	
	@Override
	public Admin createUser( Admin admin ) throws AdminException {
		
		return  repo.save(admin);
		
	}

	@Override
	public Admin  updateUser(Admin admin, String key) throws AdminException {
		
	CurrentSessionUser loggedinUser  =	srepo.findByUuid(key);
	
	if(loggedinUser == null) {
		
		throw new AdminException("Please provide a valid key to update Admin");
	}
	
	if( loggedinUser.getUserid() == admin.getAdminId()) {
	
		return repo.save(admin);
		
		
	}else {
		
		throw new AdminException("Invalid Admin details" );
		
	}
	
		
	}

}
