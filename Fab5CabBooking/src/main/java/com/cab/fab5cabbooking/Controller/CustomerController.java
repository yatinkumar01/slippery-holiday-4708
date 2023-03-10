package com.cab.fab5cabbooking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cab.fab5cabbooking.Exceptions.AdminException;
import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Exceptions.LoginException;
import com.cab.fab5cabbooking.Model.Customer;
import com.cab.fab5cabbooking.Model.Customer;
import com.cab.fab5cabbooking.Model.Login;
import com.cab.fab5cabbooking.Repository.AdminRepository;
import com.cab.fab5cabbooking.Repository.CustomerRepository;
import com.cab.fab5cabbooking.Service.AdminService;
import com.cab.fab5cabbooking.Service.CustomerLoginService;
import com.cab.fab5cabbooking.Service.CustomerService;
import com.cab.fab5cabbooking.Service.AdminLoginService;

import javax.validation.Valid;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    @Autowired
    CustomerLoginService loginservice;

    @Autowired
    private CustomerRepository repo;

    @PostMapping("/Customer")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) throws CustomerException {

        Customer existingcustomer = repo.findByUsername(customer.getUsername());

        if (existingcustomer != null) {
            throw new CustomerException("Customer  is registered with this username. ");
        }
        return new ResponseEntity<>(service.registerCustomer(customer), HttpStatus.CREATED);
    }

    @PutMapping("/UpdateCustomer/{customerId}")
    public ResponseEntity<Customer> updateAdmin(@Valid @RequestBody Customer customer, @PathVariable Integer customerId) throws CustomerException {
        return new ResponseEntity<>(service.updateCustomer(customer, customerId), HttpStatus.OK);
    }

    @PostMapping("/CustomerLogin")
    public ResponseEntity<String> CustomerLogin(@Valid @RequestBody Login login) throws CustomerException, LoginException {
        return new ResponseEntity<>(loginservice.login(login), HttpStatus.OK);
    }

    @GetMapping("/CustomerLogout")
    public ResponseEntity<String> CustomerLogout(@RequestParam(required = false) String key) throws CustomerException, LoginException {
        return new ResponseEntity<>(loginservice.logOut(key), HttpStatus.OK);
    }
}
