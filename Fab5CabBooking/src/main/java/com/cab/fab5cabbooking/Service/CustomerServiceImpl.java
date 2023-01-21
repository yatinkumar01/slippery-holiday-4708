package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Model.Customer;
import com.cab.fab5cabbooking.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer registerCustomer(Customer customer) throws CustomerException {

        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer, int Id) throws CustomerException {

        Optional<Customer> customers = customerRepository.findById(Id);

        if (customers.isPresent()) {
            return customerRepository.save(customer);
        }
        throw new CustomerException("Customer not found with ID : " + Id);
    }

    @Override
    public Customer deleteCustomer(Integer customerId) throws CustomerException {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerException("Customer not found with ID : " + customerId));
        customerRepository.delete(customer);
        return customer;
    }

    @Override
    public List<Customer> viewCustomers() throws CustomerException {

        List<Customer> list = customerRepository.findAll();

        if (list == null) {
            throw new CustomerException("Customer Not Found");
        } else {
            return list;
        }
    }

    @Override
    public Customer viewCustomer(Integer customerId) throws CustomerException {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerException("Customer not found with ID : " + customerId));
        return customer;
    }
}
