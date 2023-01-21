package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.CabException;
import com.cab.fab5cabbooking.Model.Cab;
import com.cab.fab5cabbooking.Model.CabType;
import com.cab.fab5cabbooking.Repository.CabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CabServiceImpl implements CabService {
    @Autowired
    CabRepository cabRepository;

    @Override
    public Cab registerCab(Cab cab) throws CabException {

        CabType type = cab.getCabtype();

        cab.setPerKmRate(type.providePrice());
        cab.setCapacity(type.checkCapacity());

        return cabRepository.save(cab);
    }

    @Override
    public Cab updateCab(int cabId, Cab cab) throws CabException {

        Cab cabObj = cabRepository.findById(cabId).orElseThrow(() -> new CabException("Cab does not exist with ID" + cabId));
        return cabRepository.save(cabObj);
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
