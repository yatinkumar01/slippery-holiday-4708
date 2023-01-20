package com.cab.fab5cabbooking.Repository;

import com.cab.fab5cabbooking.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


}
