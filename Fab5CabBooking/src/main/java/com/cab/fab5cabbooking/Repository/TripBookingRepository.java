package com.cab.fab5cabbooking.Repository;

import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Model.TripBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TripBookingRepository extends JpaRepository<TripBooking, Integer> {

    @Query("SELECT T FROM TripBooking T WHERE T.customerId =:customerId")
    public List<TripBooking> getAllCustomerTrips(Integer customerId) throws CustomerException;

    @Query("SELECT T FROM TripBooking T WHERE T.customerId =:customerId AND BETWEEN T.fromDateTime=:startDate AND " +
            "T.toDateTime=:endDate ORDER BY T.customerId")
    public List<TripBooking> getTripsBetweenDates(Integer customerId, LocalDateTime startDate, LocalDateTime endDate);
}
