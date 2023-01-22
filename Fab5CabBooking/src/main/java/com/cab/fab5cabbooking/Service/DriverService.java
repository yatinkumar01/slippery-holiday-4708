package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.CabException;
import com.cab.fab5cabbooking.Exceptions.DriverException;
import com.cab.fab5cabbooking.Exceptions.LoginException;
import com.cab.fab5cabbooking.Model.Driver;
import com.cab.fab5cabbooking.Model.Login;

import java.util.List;

public interface DriverService {

    public Driver registerDriver(Driver driver, String key) throws DriverException, LoginException;

    public Driver updateDriver(Driver driver, int driverId, String key) throws DriverException, LoginException, CabException;

    public Driver deleteDriver(int driverId, String key) throws DriverException, LoginException;

    public List<Driver> viewBestDrivers() throws DriverException;

    public Driver viewDriver(int driverId, String key) throws DriverException, LoginException;
}
