package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.DriverNotFoundException;
import com.cab.fab5cabbooking.Model.Cab;
import com.cab.fab5cabbooking.Model.Driver;
import com.cab.fab5cabbooking.Repository.CabRepository;
import com.cab.fab5cabbooking.Repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    CabRepository cabRepository;

    @Override
    public Driver addDriver(Driver driver) {

        /* this method allows us to add the driver and assign a cab to that driver */

        Cab cab = driver.getCab();
        cab.setDriverId(driver.getDriverId());
        driver.setCab(cab);

        return driverRepository.save(driver);
    }

    @Override
    public Driver updateDriver(Driver driver) throws DriverNotFoundException {

        /* with this method we can update a particular details */

        Optional<Driver> opt = driverRepository.findById(driver.getDriverId());

        if (opt.isPresent()) {

            Driver driver1 = opt.get();
            return driverRepository.save(driver);
        }
        throw new DriverNotFoundException("No driver found with given driver details!");
    }

    @Override
    public Driver deleteDriver(Integer driverId) throws DriverNotFoundException {

        /* we can delete a driver from our database with the help of his id */

        Optional<Driver> opt = driverRepository.findById(driverId);

        if (opt.isPresent()) {

            Driver driver = opt.get();
            driverRepository.delete(driver);
            return driver;
        }
        throw new DriverNotFoundException("No driver found with given driver details!");

    }

    @Override
    public List<Driver> viewBestDrivers() throws DriverNotFoundException {

        /*
         * this method allows us to find the best drivers list i.e., those who have the
         * rating more than 4.5 */

        List<Driver> driverList = driverRepository.findAll();

        if (driverList.size() != 0) {

//            List<Driver> bestDrivers = driverList.stream().filter(driver -> driver.getRating() > 4.5).collect(Collectors.toList());

            List<Driver> bestDrivers = driverList.stream().filter(driver -> driver.getRating() > 4.5).toList();

            return bestDrivers;
        }
        throw new DriverNotFoundException("No driver details found with given Criteria!");

    }

    @Override
    public Driver viewDriver(Integer driverId) throws DriverNotFoundException {

        /* we can get the driver details in our database based on the assigned driver id with the
         * help of this method */

        Optional<Driver> opt = driverRepository.findById(driverId);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new DriverNotFoundException("No driver found with given driver id -> " + driverId);

    }
}
