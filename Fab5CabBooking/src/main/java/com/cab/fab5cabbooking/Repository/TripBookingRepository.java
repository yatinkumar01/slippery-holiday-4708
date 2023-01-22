package com.cab.fab5cabbooking.Repository;

import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Exceptions.TripException;
import com.cab.fab5cabbooking.Model.Customer;
import com.cab.fab5cabbooking.Model.TripBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface TripBookingRepository extends JpaRepository<TripBooking, Integer> {

   /* @Query("select * from TripBooking where customerId=:cid")
    public List<TripBooking> getAllCustomerTrips(Integer cid) throws CustomerException;*/

    /*@Query("SELECT T FROM TripBooking T WHERE T.customerId =:customerId AND BETWEEN T.fromDateTime=:startDate AND " +
            "T.toDateTime=:endDate ORDER BY T.customerId")*/
    /*@Query("select t from TripBooking t where t.customer.customerId=?1 AND DATE BETWEEN t.fromDate=?2 and t.endDate=?3")
    public List<TripBooking> getTripsBetweenDates(Integer customerId, LocalDate startDate, LocalDate endDate);*/

    /*@Query("select T from trip_booking T where T.customer_customer_id=:customerId")
    public Set<TripBooking> viewAllTrips(Integer customerId) throws CustomerException, TripException;*/

    public Set<TripBooking> findByCustomer(Customer customer);
}
