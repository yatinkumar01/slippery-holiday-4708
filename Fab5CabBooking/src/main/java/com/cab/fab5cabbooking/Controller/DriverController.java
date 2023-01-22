package com.cab.fab5cabbooking.Controller;

import com.cab.fab5cabbooking.Exceptions.CabException;
import com.cab.fab5cabbooking.Exceptions.DriverException;
import com.cab.fab5cabbooking.Exceptions.LoginException;
import com.cab.fab5cabbooking.Model.Driver;
import com.cab.fab5cabbooking.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("driver")
public class DriverController {
    @Autowired
    DriverService ds;

    @PostMapping("/addDriver/{key}")
    public ResponseEntity<Driver> addDriverDetailsHandler(@Valid @RequestBody Driver driver, @PathVariable String key) throws DriverException, LoginException {

        return new ResponseEntity<>(ds.registerDriver(driver, key), HttpStatus.OK);
    }

    @PutMapping("/updateDriver/{driverId}/{key}")
    public ResponseEntity<Driver> updateDriverDetailsHandler(@Valid @RequestBody Driver driver, @PathVariable Integer driverId, @PathVariable String key) throws DriverException, LoginException, CabException {

        return new ResponseEntity<>(ds.updateDriver(driver, driverId, key), HttpStatus.OK);
    }

    @DeleteMapping("/deleteDriver/{driverId}/{key}")
    public ResponseEntity<Driver> deleteDriverDetailsHandler(@PathVariable Integer driverId, @PathVariable String key) throws DriverException, LoginException {

        return new ResponseEntity<>(ds.deleteDriver(driverId, key), HttpStatus.OK);
    }

    @GetMapping("/getBestDrivers")
    public ResponseEntity<List<Driver>> getBestDriversListHandler() throws DriverException {

        return new ResponseEntity<>(ds.viewBestDrivers(), HttpStatus.OK);
    }

    @GetMapping("/viewDriver/{driverId}/{key}")
    public ResponseEntity<Driver> getBestDriversListHandler(@PathVariable Integer driverId, @PathVariable String key) throws DriverException, LoginException {

        return new ResponseEntity<>(ds.viewDriver(driverId, key), HttpStatus.OK);
    }

}
