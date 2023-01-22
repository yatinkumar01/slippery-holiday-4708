package com.cab.fab5cabbooking.Controller;

import com.cab.fab5cabbooking.Exceptions.CabException;
import com.cab.fab5cabbooking.Exceptions.LoginException;
import com.cab.fab5cabbooking.Model.Cab;
import com.cab.fab5cabbooking.Model.CabType;
import com.cab.fab5cabbooking.Service.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CabController {
    @Autowired
    public CabService cabService;

    @PostMapping("/registercab/{key}")
    public ResponseEntity<Cab> registerCabHandler(@Valid @RequestBody Cab cab, @PathVariable String key) throws CabException, LoginException {
        return new ResponseEntity<>(cabService.registerCab(cab, key), HttpStatus.OK);
    }

    @PutMapping("/updatecab/{cabId}/{key}")
    public ResponseEntity<Cab> updateCabHandler(@PathVariable Integer cabId, @PathVariable String key, @RequestBody Cab cab) throws CabException, LoginException {
        return new ResponseEntity<>(cabService.updateCab(cabId, cab,key), HttpStatus.OK);
    }

    @DeleteMapping("/deletecab/{cabId}")
    public ResponseEntity<Cab> deleteCabHandler(@PathVariable("cabId") int cabId) throws CabException {
        return new ResponseEntity<>(cabService.deleteCab(cabId), HttpStatus.OK);
    }

    @GetMapping("/cabs/{carType}")
    public ResponseEntity<List<Cab>> CabTypeHandler(@PathVariable("carType") CabType carType) throws CabException {
        return new ResponseEntity<>(cabService.viewCabsOfType(carType), HttpStatus.OK);
    }

    @GetMapping("/cab/{carType}")
    public ResponseEntity<Integer> NumberOfCabTypeHandler(@PathVariable("carType") CabType carType) throws CabException {
        return new ResponseEntity<>(cabService.countCabsOfType(carType), HttpStatus.OK);
    }
}
