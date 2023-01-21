package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.DriverNotFoundException;
import com.cab.fab5cabbooking.Model.Driver;

import java.util.List;

public interface DriverService {

    public Driver addDriver(Driver driver);

    public Driver updateDriver(Driver driver) throws DriverNotFoundException;

    public Driver deleteDriver(Integer driverId) throws DriverNotFoundException;

    public List<Driver> viewBestDrivers() throws DriverNotFoundException;

    public Driver viewDriver(Integer driverId) throws DriverNotFoundException;


}
