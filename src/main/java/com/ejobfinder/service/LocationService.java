package com.ejobfinder.service;

import com.ejobfinder.model.Location;

import java.util.List;

/**
 * Created by Le on 1/25/2016.
 */
public interface LocationService {

    void addLocation(Location location);

    Location getLocationById(String locationId);

    List<Location> getAllLocations();

    Location getLocationByCity(String city);
}
