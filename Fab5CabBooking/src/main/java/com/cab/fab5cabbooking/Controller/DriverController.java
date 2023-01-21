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
    public ResponseEntity<Driver> addDriverDetailsHandler(@RequestBody Driver driver) throws DriverException {

        return new ResponseEntity<>(ds.registerDriver(driver), HttpStatus.OK);
    }

    @PutMapping("/updateDriver/{driverId}")
    public ResponseEntity<Driver> updateDriverDetailsHandler(@RequestBody Driver driver, @PathVariable Integer driverId) throws DriverException {

        return new ResponseEntity<>(ds.updateDriver(driver, driverId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteDriver/{driverId}")
    public ResponseEntity<Driver> deleteDriverDetailsHandler(@PathVariable Integer driverId) throws DriverException {

        return new ResponseEntity<>(ds.deleteDriver(driverId), HttpStatus.OK);
    }

    @GetMapping("/getBestDrivers")
    public ResponseEntity<List<Driver>> getBestDriversListHandler() throws DriverException {

        return new ResponseEntity<>(ds.viewBestDrivers(), HttpStatus.OK);
    }

    @GetMapping("/viewDriver/{driverId}")
    public ResponseEntity<Driver> getBestDriversListHandler(@PathVariable Integer driverId) throws DriverException {

        return new ResponseEntity<>(ds.viewDriver(driverId), HttpStatus.OK);
    }

}
