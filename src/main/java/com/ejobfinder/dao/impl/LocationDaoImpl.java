package com.ejobfinder.dao.impl;

import com.ejobfinder.dao.LocationDao;
import com.ejobfinder.model.Location;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class LocationDaoImpl implements LocationDao {

    private SessionFactory sessionFactory;

    @Autowired
    public LocationDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addLocation(Location location) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(location);
        session.flush();
    }

    @Override
    public Location getLocationById(String id) {
        Session session = sessionFactory.getCurrentSession();
        Location location = (Location) session.get(Location.class, id);
        session.flush();

        return location;
    }

    @Override
    public Location getLocationByCity(String city) {
        Session session = sessionFactory.getCurrentSession();
        Query query = sessionFactory.getCurrentSession().
                createQuery("from Location where city=:city");
        query.setParameter("city", city);
        Location location = (Location) query.uniqueResult();
        session.flush();

        return location;
    }

    @Override
    public List<Location> getAllLocations() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Location");
        List<Location> locationList = query.list();
        session.flush();
        return locationList;
    }

    @Override
    public void deleteLocation(String id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getLocationById(id));
        session.flush();
    }
}
