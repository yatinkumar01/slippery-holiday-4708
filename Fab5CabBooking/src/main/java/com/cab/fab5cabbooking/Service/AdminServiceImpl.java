package com.cab.fab5cabbooking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.fab5cabbooking.Exceptions.AdminException;
import com.cab.fab5cabbooking.Model.AbstractUser;
import com.cab.fab5cabbooking.Model.Admin;
import com.cab.fab5cabbooking.Repository.AdminRepository;
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository repo;
	
	@Override
	public Admin createUser( Admin admin ) throws AdminException {
		
		return  repo.save(admin);
		
	}

	@Override
	public Admin  updateUser(Admin admin, Integer id) throws AdminException {
		
	Admin a  =	repo.findById(id).orElseThrow(()-> new AdminException("Admin not found with this id : "+admin.getAdminId()
	                                                                            +" Please enter correct Adminid"));
		
	return repo.save(admin);
	}

}
