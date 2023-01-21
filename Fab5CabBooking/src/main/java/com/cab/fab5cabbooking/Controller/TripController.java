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
import java.util.Set;

@RestController
@RequestMapping("trip")
public class TripController {

    @Autowired
    private TripBookingService tbs;

    @PostMapping("/addTrip/{id}")
    public ResponseEntity<TripBooking> addTripDetailsHandler(@RequestBody TripBooking tripBooking, @PathVariable Integer id) throws CustomerException, DriverException {

        return new ResponseEntity<>(tbs.addTrip(tripBooking, id), HttpStatus.OK);
    }

    @PutMapping("/updateTrip")
    public ResponseEntity<TripBooking> updateTripDetailsHandler(@RequestBody TripBooking tripBooking) throws TripException {

        return new ResponseEntity<>(tbs.updateTrip(tripBooking), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTrip/{id}")
    public ResponseEntity<TripBooking> deleteTripHandler(@PathVariable Integer id) throws TripException {

        return new ResponseEntity<>(tbs.deleteTrip(id), HttpStatus.OK);
    }

    @GetMapping("/customerTrip/{id}")
    public ResponseEntity<Set<TripBooking>> viewCustomerTripHandler(@PathVariable Integer id) throws TripException, CustomerException {

        return new ResponseEntity<>(tbs.viewTripOfCustomer(id), HttpStatus.OK);
    }

    @PutMapping("/endTrip/{id}")
    public ResponseEntity<TripBooking> endTripHandler(@PathVariable Integer id) throws TripException {

        return new ResponseEntity<>(tbs.endTrip(id), HttpStatus.OK);
    }

    @GetMapping("/tripBill/{customerId}")
    public ResponseEntity<String> getTripBillHandler(@PathVariable Integer customerId) throws CustomerException, TripException {

        return new ResponseEntity<>(tbs.calculateBillAmount(customerId), HttpStatus.OK);
    }

    /* admin methods */

    @GetMapping("/tripByCabWise/{cabType}")
    public ResponseEntity<List<TripBooking>> getTripsByCabTypesHandler(@PathVariable String cabType) throws TripException {

        return new ResponseEntity<>(tbs.getAllTripsCabWise(cabType), HttpStatus.OK);
    }

    @GetMapping("/getAllTrips")
    public ResponseEntity<List<TripBooking>> getAllTripsHandler() throws TripException {

        return new ResponseEntity<>(tbs.getAllTrips(), HttpStatus.OK);
    }

    /*@GetMapping("/getTripByDates/{customerId}")
    public ResponseEntity<List<TripBooking>> getTripsInBetweenDatesHandler(@PathVariable Integer customerId, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) throws TripException {

        return new ResponseEntity<>(tbs.gitTripsBetweenDaysForACustomer(customerId, startDate, endDate), HttpStatus.OK);
    }*/
}
