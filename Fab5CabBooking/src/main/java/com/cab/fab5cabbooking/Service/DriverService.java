package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.DriverException;
import com.cab.fab5cabbooking.Model.Driver;

import java.util.List;

public interface DriverService {

    public Driver registerDriver(Driver driver) throws DriverException;

    public Driver updateDriver(Driver driver, int driverId) throws DriverException;

    public Driver deleteDriver(int driverId) throws DriverException;

    public List<Driver> viewBestDrivers() throws DriverException;

    public Driver viewDriver(int driverId) throws DriverException;
}
