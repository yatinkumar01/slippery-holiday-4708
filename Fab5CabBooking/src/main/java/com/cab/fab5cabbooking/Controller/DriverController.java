package com.cab.fab5cabbooking.Controller;

import com.cab.fab5cabbooking.Exceptions.DriverException;
import com.cab.fab5cabbooking.Model.Driver;
import com.cab.fab5cabbooking.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("driver")
public class DriverController {

    @Autowired
    DriverService ds;

    @PostMapping("/addDriver")
    public ResponseEntity<Driver> addDriverDetails(@RequestBody Driver driver) throws DriverException {

        return new ResponseEntity<>(ds.registerDriver(driver), HttpStatus.OK);
    }

    @PutMapping("/updateDriver/{driverId}")
    public ResponseEntity<Driver> updateDriverDetails(@RequestBody Driver driver, @PathVariable Integer driverId) throws DriverException {

        return new ResponseEntity<>(ds.updateDriver(driver, driverId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteDriver/{driverId}")
    public ResponseEntity<Driver> deleteDriverDetails(@PathVariable Integer driverId) throws DriverException {

        return new ResponseEntity<>(ds.deleteDriver(driverId), HttpStatus.OK);
    }

    @GetMapping("/getBestDrivers")
    public ResponseEntity<List<Driver>> getBestDriversList() throws DriverException {

        return new ResponseEntity<>(ds.viewBestDrivers(), HttpStatus.OK);
    }

    @GetMapping("/viewDriver/{driverId}")
    public ResponseEntity<Driver> getBestDriversList(@PathVariable Integer driverId) throws DriverException {

        return new ResponseEntity<>(ds.viewDriver(driverId), HttpStatus.OK);
    }

}
