package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Exceptions.DriverNotFoundException;
import com.cab.fab5cabbooking.Exceptions.TripNotFoundException;
import com.cab.fab5cabbooking.Model.Cab;
import com.cab.fab5cabbooking.Model.Customer;
import com.cab.fab5cabbooking.Model.Driver;
import com.cab.fab5cabbooking.Model.TripBooking;
import com.cab.fab5cabbooking.Repository.CabRepository;
import com.cab.fab5cabbooking.Repository.CustomerRepository;
import com.cab.fab5cabbooking.Repository.DriverRepository;
import com.cab.fab5cabbooking.Repository.TripBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TripBookingServiceImpl implements TripBookingService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TripBookingRepository tripBookingRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    CabRepository cabRepository;


    @Override
    public TripBooking addTrip(TripBooking tripBooking) throws CustomerException {

        Optional<Customer> opt1 = customerRepository.findById(tripBooking.getCustomer().getCustomerId());

        if (opt1.isPresent()) {
            Customer customer = opt1.get();

            /* to find out the best driver with rating > 4.5 and assign it to the customers trip
             * get cab from driver and assign to the trip */

            List<Driver> drivers = new DriverServiceImpl().viewBestDrivers();

            if (drivers.size() != 0) {

                /* get the best driver*/
                Driver driver = drivers.get(0);

                /* get the cab for driver and make cab not available until the trip ends */
                Cab cab = driver.getCab();
                cab.setCabAvailable(false);
                driver.setCab(cab);

                /* set bill as distance entered in km multiplied by cab charges per km */
                Double fare = tripBooking.getDistanceInKm() * cab.getPerKmRate();
                tripBooking.setBill(fare);
                tripBooking.setDriver(driver);

                /* now assign the trip to customer where he enters destination and source with distance to travel*/

                customer.setTripBooking(tripBooking);
                tripBooking.setCustomer(customer);

                return tripBookingRepository.save(tripBooking);
            }
            throw new DriverNotFoundException("No drivers available right now! please try again some time.");
        }
        throw new CustomerException("No customer found with given trip details! please enter valid customer details!");
    }

    @Override
    public TripBooking updateTrip(TripBooking tripBooking) throws TripNotFoundException {

        /*
         * here we can update the details of trip when ever we change the destination and that also
         * reflects the bill amount
         * */

        Optional<TripBooking> opt = tripBookingRepository.findById(tripBooking.getTripBookingId());

        if (opt.isPresent()) {

            TripBooking tripBooking1 = opt.get();

            /* here we assign the updated destination and its the  price */

            Double distance = tripBooking.getDistanceInKm();
            Double fare = tripBooking.getDriver().getCab().getPerKmRate();

            tripBooking.setBill(distance * fare);

            return tripBookingRepository.save(tripBooking);
        }
        throw new TripNotFoundException("No trips found with given trip details");

    }

    @Override
    public TripBooking deleteTrip(TripBooking tripBooking) throws TripNotFoundException {

        /*
         * this method is intended for internal use*/

        Optional<TripBooking> opt = tripBookingRepository.findById(tripBooking.getTripBookingId());

        if (opt.isPresent()) {
            TripBooking tb = opt.get();
            tb.setDriver(null);
            tripBookingRepository.delete(tb);
            return tb;
        }
        throw new TripNotFoundException("No trips found with given trip details");

    }

    @Override
    public TripBooking viewTripOfCustomer(Integer customerId) throws TripNotFoundException, CustomerException {

        /*
         * this method gets all the trip details of a customer based on his id*/

        Optional<Customer> opt = customerRepository.findById(customerId);

        if (opt.isPresent()) {
            Customer customer = opt.get();

            TripBooking tripBooking = customer.getTripBooking();

            if (tripBooking != null) {
                return tripBooking;
            }
            throw new TripNotFoundException("No trips found, you can add by booking trip!!");
        }
        throw new CustomerException("No customer found with given customer id -> " + customerId);
    }

    @Override
    public TripBooking endTrip(Integer tripId) throws TripNotFoundException {

        /* this method ends the trip of a customer by finding the trip by its id and
         * making the cab status available for another booking and also generate the
         * bill amount for distance travelled and set the payment status paid*/

        Optional<TripBooking> opt = tripBookingRepository.findById(tripId);

        if (opt.isPresent()) {

            TripBooking tripBooking = opt.get();

            Driver driver = tripBooking.getDriver();
            Cab cab = driver.getCab();
            cab.setCabAvailable(true);

            driverRepository.save(driver);
            tripBooking.setDriver(driver);
            tripBookingRepository.save(tripBooking);

            return tripBooking;
        }
        throw new TripNotFoundException("No trip found with given trip id -> " + tripId);

    }

    @Override
    public String calculateBillAmount(Integer customerId) throws TripNotFoundException, CustomerException {

        /*
         * this method gives the total bill amount of a particular trip of a customer*/

        Optional<Customer> opt = customerRepository.findById(customerId);

        if (opt.isPresent()) {

            Customer customer = opt.get();

            TripBooking tripBooking = customer.getTripBooking();

            if (tripBooking == null) {
                throw new TripNotFoundException("No trips found for the customer with given id -> " + customerId);
            }
            return "Amount for the Trip -> " + tripBooking.getBill();
        }
        throw new CustomerException("No customer found with given id -> " + customerId);

    }

    /* admin methods  */

    @Override
    public List<TripBooking> getAllTripsCabWise(String cabType) throws TripNotFoundException {

        /*this method gets all the trips for cabs that are registered*/

        List<TripBooking> allTips = tripBookingRepository.findAll();

        List<TripBooking> listByCabType = allTips.stream().filter(t -> t.getDriver().getCab().getCabtype().equals(cabType)).toList();

        if (listByCabType.size() != 0) {
            return listByCabType;
        }
        throw new TripNotFoundException("No trips found with given cab type -> " + cabType);

    }

    @Override
    public List<TripBooking> getAllTrips() throws TripNotFoundException {

        /*this method gets all the trips of all the customers*/

        return tripBookingRepository.findAll();
    }

    @Override
    public List<TripBooking> gitTripsBetweenDaysForACustomer(Integer customerId, LocalDateTime startDate, LocalDateTime endDate) throws TripNotFoundException {

        /*this method gives the result of trips in between dates for a particular customer*/

        List<TripBooking> tripList = tripBookingRepository.getTripsBetweenDates(customerId, startDate, endDate);

        if (tripList.size() == 0) {
            throw new TripNotFoundException("No trips found in between given dates");
        }
        return tripList;
    }
}
