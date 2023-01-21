package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.AdminException;
import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Exceptions.LoginException;
import com.cab.fab5cabbooking.Model.Admin;
import com.cab.fab5cabbooking.Model.Customer;
import com.cab.fab5cabbooking.Model.Login;

public interface CustomerLoginService {
public String login(Login login)throws LoginException,CustomerException;

	
	public String logOut(String key)throws LoginException;
	
	
}
