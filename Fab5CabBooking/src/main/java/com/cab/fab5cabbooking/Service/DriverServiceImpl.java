package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.CabException;
import com.cab.fab5cabbooking.Exceptions.DriverException;
import com.cab.fab5cabbooking.Exceptions.LoginException;
import com.cab.fab5cabbooking.Model.Cab;
import com.cab.fab5cabbooking.Model.CabType;
import com.cab.fab5cabbooking.Model.Driver;
import com.cab.fab5cabbooking.Repository.CabRepository;
import com.cab.fab5cabbooking.Repository.CurrentUserSessionRepository;
import com.cab.fab5cabbooking.Repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    CabRepository cabRepository;

    @Autowired
    CurrentUserSessionRepository cService;

    public boolean isLogin(String key) throws LoginException {

        String role = cService.findByUuid(key).getRole();

        if (role.equals("Admin")) {
            return true;
        }
        throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");
    }

    @Override
    public Driver registerDriver(Driver driver, String key) throws DriverException, LoginException {

        if (isLogin(key)) {
            Cab cab = driver.getCab();
            CabType cabtype = cab.getCabtype();
            cab.setSittingCapacity(cabtype.sittingCapacity());
            cab.setPerKmRate(cabtype.providePrice());
            driver.setCab(cab);

            return driverRepository.save(driver);
        }
        throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");

    }

    @Override
    public Driver updateDriver(Driver driver, int driverId, String key) throws DriverException, LoginException, CabException {

        if (isLogin(key)) {

            Driver driverObj = driverRepository.findById(driverId).orElseThrow(() -> new DriverException("Driver doesn't exist with id : " + driverId));

            Integer cabId = driver.getCab().getCabId();

            Cab cab = cabRepository.findById(cabId).orElseThrow(() -> new CabException("No Cabs found"));

            CabType cabtype = cab.getCabtype();
            cab.setSittingCapacity(cabtype.sittingCapacity());
            cab.setPerKmRate(cabtype.providePrice());

            driverObj.setCab(cab);

            driverObj.setLicenceNo(driver.getLicenceNo());
            driverObj.setRating(driver.getRating());
            driverRepository.save(driverObj);
            return driverObj;
        }
        throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");

    }

    @Override
    public Driver deleteDriver(int driverId, String key) throws DriverException, LoginException {

        if (isLogin(key)) {
            Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new DriverException("Driver doesn't exist with id : " + driverId));
            driverRepository.delete(driver);
            return driver;
        }
        throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");

    }

    @Override
    public List<Driver> viewBestDrivers() throws DriverException {

        List<Driver> list = driverRepository.findAll();
        return list.stream().filter(s -> s.getRating() > 4.5).toList();
    }

    @Override
    public Driver viewDriver(int driverId, String key) throws DriverException, LoginException {

        if (isLogin(key)) {
            return driverRepository.findById(driverId).orElseThrow(() -> new DriverException("Driver doesn't exist with id :" + driverId));
        }
        throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");
    }
}
