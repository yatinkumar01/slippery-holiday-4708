package com.cab.fab5cabbooking.Repository;

import com.cab.fab5cabbooking.Model.Cab;
<<<<<<< HEAD
import com.cab.fab5cabbooking.Model.CabType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CabRepository extends JpaRepository<Cab,Integer> {

    List<Cab> findAllByCabtype(CabType cabType);
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabRepository extends JpaRepository<Cab, Integer> {

>>>>>>> 6efae28d972da61bfa84b931dd058beed419e2d9
}
