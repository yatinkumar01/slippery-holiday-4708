package com.cab.fab5cabbooking.Controller;

import java.util.Optional;

import org.apache.catalina.startup.Tomcat.ExistingStandardWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.cab.fab5cabbooking.Service.AdminLoginService;

@RestController
public class AdminController {

	@Autowired
	private AdminService service;
	
	@Autowired
	private AdminRepository repo;
	
	
	@PostMapping("Admin")
	public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) throws AdminException {
		
		Admin  exisitingAdmin =  repo.findByUsername(admin.getUsername());
		
		if (exisitingAdmin !=  null) throw new AdminException("Please chagane Username. Admin is reegistered with this username. ");
		
		Admin createdAdmin =  service.createUser(admin);

	  return new ResponseEntity<>(createdAdmin,HttpStatus.CREATED);
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	
	@PutMapping("/Admin")
	public ResponseEntity<Admin>  updateAdmin(@RequestBody Admin admin, String key) throws AdminException {
		
	
			Admin updatedAdmin = service.updateUser(admin,key ) ;
			
			return new ResponseEntity<>(updatedAdmin,HttpStatus.OK);
		
	}
	
	
	@Autowired
	AdminLoginService loginservice;
	
	
	@PostMapping("/loginAdmin")
	
	public  ResponseEntity<String>  AdminLogin(@RequestBody Login login) throws AdminException, CustomerException, LoginException {
	
		
		String result =  loginservice.login(login);
		
		return new ResponseEntity<>(result,HttpStatus.OK);
		
	}
	
	@PostMapping("/Logout")
	public ResponseEntity<String> AdminLogout(@RequestParam(required = false) String key) throws AdminException, CustomerException, LoginException {
		
      String result = loginservice.logOut(key);
	
      return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
