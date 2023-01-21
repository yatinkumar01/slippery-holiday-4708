package com.cab.fab5cabbooking.Repository;

import com.cab.fab5cabbooking.Model.Cab;
import com.cab.fab5cabbooking.Model.CabType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CabRepository extends JpaRepository<Cab,Integer> {

    List<Cab> findAllByCabtype(CabType cabType);
}
