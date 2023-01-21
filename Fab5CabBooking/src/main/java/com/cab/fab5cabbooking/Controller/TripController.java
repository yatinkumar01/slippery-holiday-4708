package com.cab.fab5cabbooking.Controller;

import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Exceptions.DriverException;
import com.cab.fab5cabbooking.Exceptions.TripException;
import com.cab.fab5cabbooking.Model.TripBooking;
import com.cab.fab5cabbooking.Service.TripBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("trip")
public class TripController {

    @Autowired
    private TripBookingService tbs;

    @PostMapping("/addTrip")
    public ResponseEntity<TripBooking> addTripDetails(@RequestBody TripBooking tripBooking) throws CustomerException, DriverException {

        return new ResponseEntity<>(tbs.addTrip(tripBooking), HttpStatus.OK);
    }

    @PutMapping("/updateTrip")
    public ResponseEntity<TripBooking> updateTripDetails(@RequestBody TripBooking tripBooking) throws TripException {

        return new ResponseEntity<>(tbs.updateTrip(tripBooking), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTrip/{id}")
    public ResponseEntity<TripBooking> deleteTrip(@PathVariable Integer id) throws TripException {

        return new ResponseEntity<>(tbs.deleteTrip(id), HttpStatus.OK);
    }

    @GetMapping("/customerTrip/{id}")
    public ResponseEntity<TripBooking> viewCustomerTrip(@PathVariable Integer id) throws TripException, CustomerException {

        return new ResponseEntity<>(tbs.viewTripOfCustomer(id), HttpStatus.OK);
    }

    @PutMapping("/endTrip/{id}")
    public ResponseEntity<TripBooking> endTrip(@PathVariable Integer id) throws TripException {

        return new ResponseEntity<>(tbs.endTrip(id), HttpStatus.OK);
    }

    @GetMapping("/tripBill/{customerId}")
    public ResponseEntity<String> getTripBill(@PathVariable Integer customerId) throws CustomerException, TripException {

        return new ResponseEntity<>(tbs.calculateBillAmount(customerId), HttpStatus.OK);
    }

    /* admin methods */

    @GetMapping("/tripByCabWise/{cabType}")
    public ResponseEntity<List<TripBooking>> getTripsByCabTypes(@PathVariable String cabType) throws TripException {

        return new ResponseEntity<>(tbs.getAllTripsCabWise(cabType), HttpStatus.OK);
    }

    @GetMapping("/getAllTrips")
    public ResponseEntity<List<TripBooking>> getAllTrips() throws TripException {

        return new ResponseEntity<>(tbs.getAllTrips(), HttpStatus.OK);
    }

    @GetMapping("/getTripByDates/{customerId}")
    public ResponseEntity<List<TripBooking>> getTripsInBetweenDates(@PathVariable Integer customerId, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) throws TripException {

        return new ResponseEntity<>(tbs.gitTripsBetweenDaysForACustomer(customerId, startDate, endDate), HttpStatus.OK);
    }
}
