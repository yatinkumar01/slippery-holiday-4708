package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.DriverException;
import com.cab.fab5cabbooking.Model.Cab;
import com.cab.fab5cabbooking.Model.CabType;
import com.cab.fab5cabbooking.Model.Driver;
import com.cab.fab5cabbooking.Repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepository driverRepository;

    @Override
    public Driver registerDriver(Driver driver) throws DriverException {

        Cab cab = driver.getCab();
        CabType cabtype = cab.getCabtype();
        cab.setCapacity(cabtype.checkCapacity());
        cab.setPerKmRate(cabtype.providePrice());
        driver.setCab(cab);

        return driverRepository.save(driver);
    }

    @Override
    public Driver updateDriver(Driver driver, int driverId) throws DriverException {

        Driver driverObj = driverRepository.findById(driverId).orElseThrow(() -> new DriverException("Driver doesn't exist with id : " + driverId));
        driverObj.setCab(driver.getCab());
        driverObj.setLicenceNo(driver.getLicenceNo());
        driverObj.setRating(driver.getRating());
        driverRepository.save(driverObj);
        return driverObj;
    }

    @Override
    public Driver deleteDriver(int driverId) throws DriverException {

        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new DriverException("Driver doesn't exist with id : " + driverId));
        driverRepository.delete(driver);
        return driver;
    }

    @Override
    public List<Driver> viewBestDrivers() throws DriverException {

        List<Driver> list = driverRepository.findAll();
        return list.stream().filter(s -> s.getRating() > 4.5).toList();
    }

    @Override
    public Driver viewDriver(int driverId) throws DriverException {

        return driverRepository.findById(driverId).orElseThrow(() -> new DriverException("Driver doesn't exist with id :" + driverId));
    }
}
