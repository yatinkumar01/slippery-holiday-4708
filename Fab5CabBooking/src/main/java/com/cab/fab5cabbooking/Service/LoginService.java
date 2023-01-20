package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.AdminException;
import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Exceptions.LoginException;
import com.cab.fab5cabbooking.Model.Login;

public interface LoginService {

	public String login(Login login)throws AdminException, CustomerException,LoginException;

	
	public String logOut(String key)throws AdminException, CustomerException,LoginException;
	
	
	
}
