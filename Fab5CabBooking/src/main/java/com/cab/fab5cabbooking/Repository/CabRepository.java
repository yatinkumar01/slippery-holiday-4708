package com.cab.fab5cabbooking.Repository;

import com.cab.fab5cabbooking.Model.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabRepository extends JpaRepository<Cab, Integer> {

}
