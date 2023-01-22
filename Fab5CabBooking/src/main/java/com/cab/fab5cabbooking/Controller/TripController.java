package com.cab.fab5cabbooking.Controller;

import com.cab.fab5cabbooking.Exceptions.CustomerException;
import com.cab.fab5cabbooking.Exceptions.DriverException;
import com.cab.fab5cabbooking.Exceptions.LoginException;
import com.cab.fab5cabbooking.Exceptions.TripException;
import com.cab.fab5cabbooking.Model.TripBooking;
import com.cab.fab5cabbooking.Service.TripBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("trip")
public class TripController {

    @Autowired
    private TripBookingService tbs;

    @PostMapping("/addTrip/{key}")
    public ResponseEntity<TripBooking> addTripDetailsHandler(@Valid @RequestBody TripBooking tripBooking, @PathVariable String key) throws CustomerException, DriverException, LoginException {

        return new ResponseEntity<>(tbs.addTrip(tripBooking, key), HttpStatus.OK);
    }

    @PutMapping("/updateTrip/{key}")
    public ResponseEntity<TripBooking> updateTripDetailsHandler(@Valid @RequestBody TripBooking tripBooking, @PathVariable String key) throws TripException, DriverException, CustomerException, LoginException {

        return new ResponseEntity<>(tbs.updateTrip(tripBooking, key), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTrip/{tripId}/{key}")
    public ResponseEntity<TripBooking> deleteTripHandler(@PathVariable Integer tripId, @PathVariable String key) throws TripException, LoginException {

        return new ResponseEntity<>(tbs.deleteTrip(tripId, key), HttpStatus.OK);
    }

    @PutMapping("/endTrip/{tripId}/{key}")
    public ResponseEntity<String> endTripHandler(@PathVariable Integer tripId, @PathVariable String key) throws TripException, LoginException {

        return new ResponseEntity<>(tbs.endTrip(tripId, key), HttpStatus.OK);
    }

    @GetMapping("/tripBill/{tripId}/{key}")
    public ResponseEntity<String> getTripBillHandler(@PathVariable Integer tripId, @PathVariable String key) throws CustomerException, TripException, LoginException {

        return new ResponseEntity<>(tbs.calculateBillAmount(tripId, key), HttpStatus.OK);
    }

    /* admin methods */

    @GetMapping("/getAllTrips")
    public ResponseEntity<List<TripBooking>> getAllTripsHandler() throws TripException {

        return new ResponseEntity<>(tbs.getAllTrips(), HttpStatus.OK);
    }

}
