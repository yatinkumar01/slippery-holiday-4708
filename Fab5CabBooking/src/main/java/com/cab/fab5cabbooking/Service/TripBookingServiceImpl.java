package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Exceptions.DriverException;
import com.cab.fab5cabbooking.Exceptions.LoginException;
import com.cab.fab5cabbooking.Exceptions.TripException;
import com.cab.fab5cabbooking.Model.Cab;
import com.cab.fab5cabbooking.Model.Customer;
import com.cab.fab5cabbooking.Model.Driver;
import com.cab.fab5cabbooking.Model.TripBooking;
import com.cab.fab5cabbooking.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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

    @Autowired
    DriverService driverService;

    @Autowired
    CurrentUserSessionRepository cService;

    /*
    *
    * Integer customerId = cService.findByUuid(key).getUserid();

        if (isLoginCustomer(key)) {

        }
        throw new LoginException("Customer with this Key is not LoggedIn. Please provide valid Key ");
    *
    * */

    public boolean isLoginAdmin(String key) throws LoginException {

        String role = cService.findByUuid(key).getRole();

        if (role.equals("Admin")) {
            return true;
        }
        throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");
    }

    public boolean isLoginCustomer(String key) throws LoginException {

        String role = cService.findByUuid(key).getRole();

        if (role.equals("Customer")) {
            return true;
        }
        throw new LoginException("Customer with this Key is not LoggedIn. Please provide valid Key ");
    }

    @Override
    public TripBooking addTrip(TripBooking tripBooking, String key) throws CustomerException, DriverException, LoginException {

        Integer customerId = cService.findByUuid(key).getUserid();

        if (isLoginCustomer(key)) {

            Optional<Customer> opt1 = customerRepository.findById(customerId);

            if (opt1.isPresent()) {
                Customer customer = opt1.get();

                List<Driver> drivers = driverService.viewBestDrivers();

                if (drivers.size() != 0) {
                    Driver driver = drivers.get(0);

                    Cab cab = driver.getCab();
                    cab.setCabAvailable(false);

                    Double fare = tripBooking.getDistanceInKm() * cab.getPerKmRate() + 60;
                    tripBooking.setBill(fare);

                    tripBooking.setDriver(driver);
                    tripBooking.setCustomer(customer);

                    return tripBookingRepository.save(tripBooking);
                }
                throw new DriverException("No drivers available right now! please try again some time.");
            }
            throw new CustomerException("No customer found with given trip details! please enter valid customer details!");
        }
        throw new LoginException("Customer with this Key is not LoggedIn. Please provide valid Key ");


    }

    @Override
    public TripBooking updateTrip(TripBooking tripBooking, String key) throws TripException, DriverException, CustomerException, LoginException {

        /*
         * here we can update the details of trip when ever we change the destination and that also
         * reflects the bill amount
         * */

        Integer customerId = cService.findByUuid(key).getUserid();

        if (isLoginCustomer(key)) {

            Optional<Customer> opt1 = customerRepository.findById(customerId);

            if (opt1.isPresent()) {
                Customer customer = opt1.get();

                List<Driver> drivers = driverService.viewBestDrivers();

                if (drivers.size() != 0) {

                    Driver driver = drivers.get(0);

                    Cab cab = driver.getCab();
                    cab.setCabAvailable(false);

                    Double fare = tripBooking.getDistanceInKm() * cab.getPerKmRate() + 60;
                    tripBooking.setBill(fare);

                    tripBooking.setDriver(driver);
                    tripBooking.setCustomer(customer);

                    return tripBookingRepository.save(tripBooking);
                }
                throw new DriverException("No drivers available right now! please try again some time.");
            }
            throw new CustomerException("No customer found with given trip details! please enter valid customer details!");
        }
        throw new LoginException("Customer with this Key is not LoggedIn. Please provide valid Key ");

    }

    @Override
    public TripBooking deleteTrip(Integer tripId, String key) throws TripException, LoginException {

        /*
         * this method is intended for internal use*/

        Integer customerId = cService.findByUuid(key).getUserid();

        if (isLoginAdmin(key)) {

            Optional<TripBooking> opt = tripBookingRepository.findById(tripId);

            if (opt.isPresent()) {
                TripBooking tb = opt.get();

                Driver driver = tb.getDriver();
                Cab cab = driver.getCab();
                cab.setCabAvailable(true);

                driverRepository.save(driver);
                return tb;
            }
            throw new TripException("No trips found with given trip details");
        }
        throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");

    }


    @Override
    public String endTrip(Integer tripId, String key) throws TripException, LoginException {

        /* this method ends the trip of a customer by finding the trip by its id and
         * making the cab status available for another booking and also generate the
         * bill amount for distance travelled and set the payment status paid*/

        if (isLoginCustomer(key)) {

            Optional<TripBooking> opt = tripBookingRepository.findById(tripId);

            if (opt.isPresent()) {

                TripBooking tripBooking = opt.get();

                Driver driver = tripBooking.getDriver();
                Cab cab = driver.getCab();
                cab.setCabAvailable(true);

                driverRepository.save(driver);
                tripBooking.setDriver(driver);
                tripBooking.setCustomer(tripBooking.getCustomer());
                tripBookingRepository.save(tripBooking);

                return "Your bill amount for the Trip -> " + tripBooking.getBill() * 1.18 + "\n Thanks for choosing our cab services, " + "we hope to see you again";
            }
            throw new TripException("No trip found with given trip id -> " + tripId);
        }
        throw new LoginException("Customer with this Key is not LoggedIn. Please provide valid Key ");
    }

    @Override
    public String calculateBillAmount(Integer tripId, String key) throws TripException, CustomerException, LoginException {

        /*
         * this method gives the total bill amount of a particular trip of a customer*/


        if (isLoginCustomer(key)) {

            Optional<TripBooking> opt = tripBookingRepository.findById(tripId);

            if (opt.isPresent()) {

                TripBooking tripBooking = opt.get();

                /* 1.18 is GST */

                Double bill = tripBooking.getBill() * 1.18;

                return "Your bill amount for the Trip -> " + bill;
            }
            throw new TripException("No Trip details found with given id -> " + tripId);
        }
        throw new LoginException("Customer with this Key is not LoggedIn. Please provide valid Key ");
    }

    /* admin methods  */


    @Override
    public List<TripBooking> getAllTrips() throws TripException {

        return tripBookingRepository.findAll();
    }
}
