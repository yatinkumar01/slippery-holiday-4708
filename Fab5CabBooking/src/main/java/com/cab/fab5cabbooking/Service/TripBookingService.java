package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Exceptions.DriverException;
import com.cab.fab5cabbooking.Exceptions.LoginException;
import com.cab.fab5cabbooking.Exceptions.TripException;
import com.cab.fab5cabbooking.Model.TripBooking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface TripBookingService {
    public TripBooking addTrip(TripBooking tripBooking, String key) throws CustomerException, DriverException, LoginException;

    public TripBooking updateTrip(TripBooking tripBooking, String key) throws TripException, DriverException, CustomerException, LoginException;

    public TripBooking deleteTrip(Integer tripId, String key) throws TripException, LoginException;

    /*public Set<TripBooking> viewTripOfCustomer(Integer customerId) throws TripException, CustomerException;*/

    public String endTrip(Integer tripId, String key) throws TripException, LoginException;

    public String calculateBillAmount(Integer tripId, String key) throws TripException, CustomerException, LoginException;

    /* admin methods */
    /*public List<TripBooking> getAllTripsCabWise(String cabType) throws TripException;*/

    public List<TripBooking> getAllTrips() throws TripException;

//    public List<TripBooking> gitTripsBetweenDaysForACustomer(Integer customerId, LocalDate startDate, LocalDate endDate) throws TripException;


}
