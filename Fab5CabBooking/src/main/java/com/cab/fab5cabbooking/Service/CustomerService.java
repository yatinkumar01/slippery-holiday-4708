package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Model.Customer;

import java.util.List;

public interface CustomerService {

     Customer registerCustomer(Customer customer) throws CustomerException;

     Customer updateCustomer(Customer customer, int Id) throws CustomerException;

     Customer deleteCustomer(Integer customerId) throws CustomerException;

     List<Customer> viewCustomers() throws CustomerException;

     Customer viewCustomer(Integer customerId) throws CustomerException;

}
