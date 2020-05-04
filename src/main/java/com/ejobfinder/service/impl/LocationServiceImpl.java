package com.ejobfinder.service.impl;

import com.ejobfinder.dao.LocationDao;
import com.ejobfinder.model.Location;
import com.ejobfinder.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private LocationDao locationDao;

    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public void addLocation(Location location) {
        locationDao.addLocation(location);
    }

    @Override
    public Location getLocationById(String locationId) {
        return locationDao.getLocationById(locationId);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    @Override
    public Location getLocationByCity(String city) {
        return locationDao.getLocationByCity(city);
    }
}
