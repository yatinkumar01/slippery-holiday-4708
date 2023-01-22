package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.CabException;
import com.cab.fab5cabbooking.Exceptions.LoginException;
import com.cab.fab5cabbooking.Model.Cab;
import com.cab.fab5cabbooking.Model.CabType;
import com.cab.fab5cabbooking.Repository.CabRepository;
import com.cab.fab5cabbooking.Repository.CurrentUserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CabServiceImpl implements CabService {
    @Autowired
    CabRepository cabRepository;

    @Autowired
    CurrentUserSessionRepository cService;

    public boolean isLoginAdmin(String key) throws LoginException {

        String role = cService.findByUuid(key).getRole();

        if (role.equals("Admin")) {
            return true;
        }
        throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");
    }

    @Override
    public Cab registerCab(Cab cab, String key) throws CabException, LoginException {

        if (isLoginAdmin(key)) {
            CabType type = cab.getCabtype();

            cab.setPerKmRate(type.providePrice());
            cab.setSittingCapacity(type.sittingCapacity());

            return cabRepository.save(cab);
        }
        throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");
    }

    @Override
    public Cab updateCab(int cabId, Cab cab, String key) throws CabException, LoginException {

        if (isLoginAdmin(key)) {

            Cab cabObj = cabRepository.findById(cabId).orElseThrow(() -> new CabException("Cab does not exist with ID" + cabId));
            CabType type = cab.getCabtype();
            cab.setPerKmRate(type.providePrice());
            cab.setSittingCapacity(type.sittingCapacity());
            return cabRepository.save(cab);
        }
        throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");
    }

    @Override
    public Cab deleteCab(int cabId) throws CabException {

        Cab cab = cabRepository.findById(cabId).orElseThrow(() -> new CabException("Cab does not exist with ID" + cabId));
        cabRepository.delete(cab);
        return cab;
    }

    @Override
    public List<Cab> viewCabsOfType(CabType CabType) throws CabException {

        List<Cab> cabList = cabRepository.findAllByCabtype(CabType);
        return cabList;
    }

    @Override
    public Integer countCabsOfType(CabType cabType) throws CabException {

        int size = cabRepository.findAllByCabtype(cabType).size();
        return size;
    }
}
