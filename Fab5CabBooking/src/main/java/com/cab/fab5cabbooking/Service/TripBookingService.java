package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Exceptions.TripNotFoundException;
import com.cab.fab5cabbooking.Model.TripBooking;

import java.time.LocalDateTime;
import java.util.List;

public interface TripBookingService {
    public TripBooking addTrip(TripBooking tripBooking) throws CustomerException;

    public TripBooking updateTrip(TripBooking tripBooking) throws TripNotFoundException;

    public TripBooking deleteTrip(TripBooking tripBooking) throws TripNotFoundException;

    public TripBooking viewTripOfCustomer(Integer customerId) throws TripNotFoundException, CustomerException;

    public TripBooking endTrip(Integer tripId) throws TripNotFoundException;

    public String calculateBillAmount(Integer customerId) throws TripNotFoundException, CustomerException;


    /* admin methods */
    public List<TripBooking> getAllTripsCabWise(String cabType) throws TripNotFoundException;

    public List<TripBooking> getAllTrips() throws TripNotFoundException;

    public List<TripBooking> gitTripsBetweenDaysForACustomer(Integer customerId, LocalDateTime startDate, LocalDateTime endDate) throws TripNotFoundException;


}
