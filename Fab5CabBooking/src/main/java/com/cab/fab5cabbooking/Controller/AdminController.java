package com.cab.fab5cabbooking.Controller;

import java.util.Optional;

import org.apache.catalina.startup.Tomcat.ExistingStandardWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cab.fab5cabbooking.Exceptions.AdminException;
import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Exceptions.LoginException;
import com.cab.fab5cabbooking.Model.Admin;
import com.cab.fab5cabbooking.Model.CurrentSessionUser;
import com.cab.fab5cabbooking.Model.Login;
import com.cab.fab5cabbooking.Repository.AdminRepository;
import com.cab.fab5cabbooking.Service.AdminService;
import com.cab.fab5cabbooking.Service.LoginService;

@RestController
public class AdminController {

	@Autowired
	private AdminService service;
	
	@Autowired
	private AdminRepository repo;
	
	/**
	 * @param admin
	 * @return
	 * @throws AdminException
	 */
	@PostMapping("Admin")
	public Admin createAdmin(@RequestBody Admin admin) throws AdminException {
		
		Admin  exisitingAdmin =  repo.findByUsername(admin.getUsername());
		
		if (exisitingAdmin !=  null) throw new AdminException("Please chagane Username. Admin is reegistered with this username. ");
		
		Admin createdAdmin =  service.createUser(admin);

	  return createdAdmin;
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	
	@PutMapping("/Admin")
	public Admin updateAdmin(@RequestBody Admin admin, int id) throws AdminException {
		
		Optional<Admin>  exisitingAdmin =  repo.findById(id);
		
		if( exisitingAdmin.isEmpty()) {
			
			throw new AdminException("Please Enter valid detail. Admin not found with this details");
			
		}else {
		
			return service.updateUser(admin,admin.getAdminId() ) ;
		}
	}
	
	
	@Autowired
	LoginService loginservice;
	
	
	@PostMapping("/loginAdmin")
	
	public String AdminLogin(@RequestBody Login login) throws AdminException, CustomerException, LoginException {
	
		
		return  loginservice.login(login);
		
		
	}
	
	@PostMapping("/Logout")
	public String AdminLogour(@RequestParam(required = false) String key) throws AdminException, CustomerException, LoginException {
		
		return loginservice.logOut(key);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
