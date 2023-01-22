package com.cab.fab5cabbooking.Controller;

import com.cab.fab5cabbooking.Repository.CurrentUserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cab.fab5cabbooking.Exceptions.AdminException;
import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Exceptions.LoginException;
import com.cab.fab5cabbooking.Model.Admin;
import com.cab.fab5cabbooking.Model.Login;
import com.cab.fab5cabbooking.Repository.AdminRepository;
import com.cab.fab5cabbooking.Service.AdminService;
import com.cab.fab5cabbooking.Service.AdminLoginService;

import javax.validation.Valid;

@RestController
public class AdminController {
    @Autowired
    private AdminService service;
    @Autowired
    AdminLoginService loginservice;
    @Autowired
    private AdminRepository repo;

    @PostMapping("Admin")
    public ResponseEntity<Admin> createAdmin(@Valid @RequestBody Admin admin) throws AdminException {

        Admin exisitingAdmin = repo.findByUsername(admin.getUsername());

        if (exisitingAdmin != null) {
            throw new AdminException("Please change Username. Admin is registered with this username. ");
        }
        return new ResponseEntity<>(service.createUser(admin), HttpStatus.CREATED);
    }

    @PutMapping("/Admin")
    public ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin, String key) throws AdminException {
        return new ResponseEntity<>(service.updateUser(admin, key), HttpStatus.OK);
    }

    @PostMapping("/loginAdmin")
    public ResponseEntity<String> AdminLogin(@Valid @RequestBody Login login) throws AdminException, CustomerException, LoginException {
        return new ResponseEntity<>(loginservice.login(login), HttpStatus.OK);
    }

    @GetMapping("/Logout")
    public ResponseEntity<String> AdminLogout(@RequestParam(required = false) String key) throws AdminException, CustomerException, LoginException {
        return new ResponseEntity<>(loginservice.logOut(key), HttpStatus.OK);
    }

    @Autowired
    CurrentUserSessionRepository cservice;

    @GetMapping("/loginAsAdmin/{key}")
    public ResponseEntity<String> loginAsAdmin(@PathVariable String key) throws LoginException {

        String role = cservice.findByUuid(key).getRole();

        if (role.equals("Admin")) {
            return new ResponseEntity<>("Admin found", HttpStatus.ACCEPTED);
        }
        throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");

    }

    @GetMapping("/loginAsCustomer/{key}")
    public ResponseEntity<String> loginAsCustomer(@PathVariable String key) throws LoginException {

        String role = cservice.findByUuid(key).getRole();

        if (role.equals("Customer")) {
            return new ResponseEntity<>("Customer found", HttpStatus.ACCEPTED);
        }
        throw new LoginException("Customer with this Key is not LoggedIn. Please provide valid Key ");

    }

}
