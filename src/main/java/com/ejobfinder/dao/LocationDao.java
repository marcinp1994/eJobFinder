package com.ejobfinder.dao;

import com.ejobfinder.model.Location;

import java.util.List;

public interface LocationDao {
    void addLocation(Location location);

    Location getLocationById(String id);

    Location getLocationByCity(String city);

    List<Location> getAllLocations();

    void deleteLocation(String id);
}
