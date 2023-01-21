package com.cab.fab5cabbooking.Service;

import com.cab.fab5cabbooking.Exceptions.CabException;
import com.cab.fab5cabbooking.Model.Cab;
import com.cab.fab5cabbooking.Model.CabType;

import java.util.List;

public interface CabService {

    public Cab registerCab(Cab cab) throws CabException;

    public Cab updateCab(int cabId, Cab cab) throws CabException;

    public Cab deleteCab(int cabId) throws CabException;

    public List<Cab> viewCabsOfType(CabType CabType) throws CabException;

    public Integer countCabsOfType(CabType cabType) throws CabException;
}
