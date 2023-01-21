package com.cab.fab5cabbooking.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.fab5cabbooking.Exceptions.AdminException;
import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Exceptions.LoginException;
import com.cab.fab5cabbooking.Model.Admin;
import com.cab.fab5cabbooking.Model.CurrentSessionUser;
import com.cab.fab5cabbooking.Model.Login;
import com.cab.fab5cabbooking.Repository.AdminRepository;
import com.cab.fab5cabbooking.Repository.CurrentUserSessionRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminLoginServiceImpl implements AdminLoginService{

	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	CurrentUserSessionRepository cRepo;
	
	@Override
	public String login(Login login) throws AdminException, CustomerException, LoginException {
		
		
	Admin existingAdmin = adminRepo.findByUsername(login.getUserName());
	
	if(existingAdmin == null) throw new LoginException( "Admin with this username is not available. Please Enter valid Username");
		
	
	
    Optional<CurrentSessionUser> validUser = cRepo.findById(existingAdmin.getAdminId());
		
    if(validUser == null) throw new LoginException("Admin already loggedin");
	
    
    
    
    if(existingAdmin.getPassword().equals(login.getPassword())) {
    	
    String key = RandomString.make(4);
    
    CurrentSessionUser currentUserSession = new CurrentSessionUser( existingAdmin.getAdminId(), key , LocalDateTime.now(),"Admin" );
    	
    		cRepo.save(currentUserSession);
    		
    		return  currentUserSession.toString();
    	
    	
    }else {
    	throw new LoginException("Please Enter valid Password");
    }
		
    
    

	}

	@Override
	public String logOut(String key) throws AdminException, CustomerException, LoginException {
	
		
		CurrentSessionUser currentUser = cRepo.findByUuid(key);
		
		if(  currentUser == null) {
			
			throw new LoginException("User not Logged in with this UUID");
			
		}
		
		cRepo.delete(currentUser);
		
		return "Logged Out !";
	}

}
