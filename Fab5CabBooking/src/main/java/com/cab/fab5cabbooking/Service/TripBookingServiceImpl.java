package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Exceptions.TripNotFoundException;
import com.cab.fab5cabbooking.Model.Customer;
import com.cab.fab5cabbooking.Model.Driver;
import com.cab.fab5cabbooking.Model.TripBooking;
import com.cab.fab5cabbooking.Repository.CustomerRepository;
import com.cab.fab5cabbooking.Repository.TripBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TripBookingServiceImpl implements TripBookingService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TripBookingRepository tripBookingRepository;

    @Override
    public TripBooking addTrip(TripBooking tripBooking) throws CustomerException {

        Optional<Customer> opt1 = customerRepository.findById(tripBooking.getCustomer().getCustomerId());

        /* to find out the best driver with rating > 4.5 and assign it to the customers trip
         * get cab from driver and assign to the trip */

        return null;
    }

    @Override
    public TripBooking updateTrip(TripBooking tripBooking) throws TripNotFoundException {

        /*
         * here we can update the details of trip when ever we change the destination and that also
         * reflects the bill amount
         * */

        return null;
    }

    @Override
    public TripBooking deleteTrip(TripBooking tripBooking) throws TripNotFoundException {

        /*
         * this method is intended for internal use*/
        return null;
    }

    @Override
    public List<TripBooking> viewAllTripsOfCustomer(Integer customerId) throws TripNotFoundException {

        /*
         * this method gets all the trip details of a customer based on his id*/

        return null;
    }

    @Override
    public TripBooking endTrip(Integer tripId) throws TripNotFoundException {

        /* this method ends the trip of a customer by finding the trip by its id and
         * making the cab status available for another booking and also generate the
         * bill amount for distance travelled and set the payment status paid*/

        return null;
    }

    @Override
    public String calculateBillAmount(Integer customerId) throws TripNotFoundException {

        /*
         * this method gives the total bill amount of a particular trip of a customer*/

        return null;
    }

    /* admin methods  */

    @Override
    public List<TripBooking> getAllTripsCabWise() throws TripNotFoundException {

        /*this method gets all the trips for cabs that are registered*/

        return null;
    }

    @Override
    public List<TripBooking> getAllTrips() throws TripNotFoundException {

        /*this method gets all the trips of all the customers*/

        return null;
    }

    @Override
    public List<TripBooking> gitTripsBetweenDaysForACustomer(Integer customerId, LocalDateTime startDate, LocalDateTime endDate) throws TripNotFoundException {

        /*this method gives the result of trips in between dates for a particular customer*/

        return null;
    }
}
