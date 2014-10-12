/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airport.dao;

import cz.muni.fi.pa165.airport.entity.Flight;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Zdenek Kanovsky
 */
@Repository
@Transactional
public class FlightDAO implements IFlightDAO {
    @PersistenceContext
    private EntityManager em;


    @Override
    public void create(Flight flight) {
        if (flight == null) {
            throw new IllegalArgumentException("Flight is null");
        }
        if (flight.getId() != null) {
            throw new IllegalArgumentException("Flight id is not null.");
        }
        if (flight.getDeparture() == null) {
            throw new IllegalArgumentException("Flight departure is null.");
        }
        if (flight.getArrival() == null) {
            throw new IllegalArgumentException("Flight arrival is null.");
        }
        
        if(flight.getDeparture().compareTo(flight.getArrival()) >= 0){
            throw new IllegalArgumentException("Flight arrival must be after its departure.");
        }
        
        if (flight.getOrigin() == null) {
            throw new IllegalArgumentException("Flight origin is null.");
        }
        if (flight.getDestination() == null) {
            throw new IllegalArgumentException("Flight destination is null.");
        }
        if (flight.getPlane() == null) {
            throw new IllegalArgumentException("Flight plane is null.");
        }
        if (flight.getStewards() == null) {
            throw new IllegalArgumentException("Flight stewards is null.");
        }
        else if(flight.getStewards().isEmpty()) {
            throw new IllegalArgumentException("Flight must have at least one steward.");
        }

        em.persist(flight);
    }

    @Override
    public void update(Flight flight) {
        if (flight == null) {
            throw new IllegalArgumentException("Flight is null");
        }
        if (flight.getId() == null) {
            throw new IllegalArgumentException("Flight id is null.");
        }
        if (flight.getDeparture() == null) {
            throw new IllegalArgumentException("Flight departure is null.");
        }
        if (flight.getArrival() == null) {
            throw new IllegalArgumentException("Flight arrival is null.");
        }
        if(flight.getDeparture().compareTo(flight.getArrival()) >= 0){
            throw new IllegalArgumentException("Flight arrival must be after its departure.");
        }
        if (flight.getOrigin() == null) {
            throw new IllegalArgumentException("Flight origin is null.");
        }
        if (flight.getDestination() == null) {
            throw new IllegalArgumentException("Flight destination is null.");
        }
        if (flight.getPlane() == null) {
            throw new IllegalArgumentException("Flight plane is null.");
        }
        if (flight.getStewards() == null) {
            throw new IllegalArgumentException("Flight stewards is null.");
        }
        else if(flight.getStewards().isEmpty()) {
            throw new IllegalArgumentException("Flight must have at least one steward.");
        }

        em.merge(flight);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null");
        }

        Flight flight = em.find(Flight.class, id);
        if (flight == null) {
            throw new IllegalArgumentException("Flight with ID " + id + "does not exist");
        }

        em.remove(flight);
    }

    @Override
    public Flight find(Long id) {
        return em.find(Flight.class, id);
    }

    @Override
    public List<Flight> getAll() {
        Query query = em.createQuery("SELECT f FROM Flight f ORDER BY f.departure DESC");
        return (List<Flight>) query.getResultList();
    }
}
