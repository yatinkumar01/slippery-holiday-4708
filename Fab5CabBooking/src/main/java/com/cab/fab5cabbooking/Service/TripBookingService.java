package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Exceptions.DriverException;
import com.cab.fab5cabbooking.Exceptions.TripException;
import com.cab.fab5cabbooking.Model.TripBooking;

import java.time.LocalDateTime;
import java.util.List;

public interface TripBookingService {
    public TripBooking addTrip(TripBooking tripBooking) throws CustomerException, DriverException;

    public TripBooking updateTrip(TripBooking tripBooking) throws TripException;

    public TripBooking deleteTrip(TripBooking tripBooking) throws TripException;

    public TripBooking viewTripOfCustomer(Integer customerId) throws TripException, CustomerException;

    public TripBooking endTrip(Integer tripId) throws TripException;

    public String calculateBillAmount(Integer customerId) throws TripException, CustomerException;


    /* admin methods */
    public List<TripBooking> getAllTripsCabWise(String cabType) throws TripException;

    public List<TripBooking> getAllTrips() throws TripException;

    public List<TripBooking> gitTripsBetweenDaysForACustomer(Integer customerId, LocalDateTime startDate, LocalDateTime endDate) throws TripException;


}
