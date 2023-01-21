package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Model.Customer;

import java.util.List;

public interface CustomerService {

    public Customer registerAdmin(Customer customer) throws CustomerException;

    public Customer updateCustomer(Customer customer, int Id) throws CustomerException;

    public Customer deleteCustomer(Integer customerId) throws CustomerException;

    public List<Customer> viewCustomers() throws CustomerException;

    public Customer viewCustomer(Integer customerId) throws CustomerException;

}
