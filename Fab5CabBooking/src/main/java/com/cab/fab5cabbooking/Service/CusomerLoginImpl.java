package com.cab.fab5cabbooking.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cab.fab5cabbooking.Exceptions.AdminException;
import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Exceptions.LoginException;
import com.cab.fab5cabbooking.Model.Admin;
import com.cab.fab5cabbooking.Model.CurrentSessionUser;
import com.cab.fab5cabbooking.Model.Customer;
import com.cab.fab5cabbooking.Model.Login;
import com.cab.fab5cabbooking.Repository.CurrentUserSessionRepository;
import com.cab.fab5cabbooking.Repository.CustomerRepository;

import net.bytebuddy.utility.RandomString;

public class CusomerLoginImpl implements CustomerLoginService {

	@Autowired
	private CustomerRepository crepo;
	
	@Autowired
	CurrentUserSessionRepository session;
	
	@Override
	public String login(Login login) throws LoginException, CustomerException {
		
		Customer existingCustomer = crepo.findByUsername(login.getUserName());
		
		if(existingCustomer == null) throw new LoginException( "Customer with this username is not available. Please Enter valid Username");
			
		
		
	    Optional<CurrentSessionUser> validUser = session.findById(existingCustomer.getCustomerId());
			
	    if(validUser == null) throw new LoginException("Customer already loggedin");
		
	    
	    
	    
	    if(existingCustomer.getPassword().equals(login.getPassword())) {
	    	
	    String key = RandomString.make(4);
	    
	    CurrentSessionUser currentUserSession = new CurrentSessionUser( existingCustomer.getCustomerId(), key , LocalDateTime.now(),"Customer" );
	    	
	    		session.save(currentUserSession);
	    		
	    		return  currentUserSession.toString();
	    	
	    	
	    }else {
	    	throw new LoginException("Please Enter valid Password");
	    }
		
	
	}

	@Override
	public String logOut(String key) throws LoginException {
CurrentSessionUser currentUser = session.findByUuid(key);
		
		if(  currentUser == null) {
			
			throw new LoginException("User not Logged in with this UUID");
			
		}
		
		session.delete(currentUser);
		
		return "Logged Out !";
	
	
	}

	
	
}
