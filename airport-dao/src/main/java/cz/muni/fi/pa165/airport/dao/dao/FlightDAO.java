/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airport.dao.dao;

import cz.muni.fi.pa165.airport.dao.entity.Flight;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author Zdenek Kanovsky
 */
@Repository
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

        flight.setArchived(true);
    }

    @Override
    public Flight find(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null");
        }

        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.archived = :archived AND f.id = :id");
        query.setParameter("archived", false);
        query.setParameter("id", id);

        List resultList = query.getResultList();

        return resultList.isEmpty() ? null : (Flight) resultList.get(0);
    }

    @Override
    public List<Flight> getAll() {
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.archived = :archived  ORDER BY f.departure DESC");
        query.setParameter("archived", false);
        return (List<Flight>) query.getResultList();
    }

    @Override
    public boolean isPlaneAvailableForFlight(Long planeId, Flight flight) {
        if (planeId == null) {
            throw new IllegalArgumentException("Plane ID is null");
        }
        if (flight == null) {
            throw new IllegalArgumentException("Flight is null");
        }
        if (flight.getDeparture() == null) {
            throw new IllegalArgumentException("Flight departure is null");
        }
        if (flight.getArrival() == null) {
            throw new IllegalArgumentException("Flight arrival is null");
        }
        if (flight.getDeparture().after(flight.getArrival())){
            throw new IllegalArgumentException("Departure is after arrival");
        }

        Query query = null;
        if (flight.getId() != null) {
            query = em.createNamedQuery(Flight.QUERY_IS_PLANE_AVAILABLE4FLIGHT_EXCLUSIVE);
            query.setParameter("flightId", flight.getId());
        } else {
            query = em.createNamedQuery(Flight.QUERY_IS_PLANE_AVAILABLE4FLIGHT);
        }

        query.setParameter("planeId", planeId);
        query.setParameter("fromT", flight.getDeparture());
        query.setParameter("toT", flight.getArrival());

        Long count = (Long) query.getSingleResult();

        return count == 0;
    }

    @Override
    public boolean isStewardAvailableForFlight(Long stewardId, Flight flight) {
        if (stewardId == null) {
            throw new IllegalArgumentException("Steward ID is null");
        }
        if (flight == null) {
            throw new IllegalArgumentException("Flight is null");
        }
        if (flight.getDeparture() == null) {
            throw new IllegalArgumentException("Flight departure is null");
        }
        if (flight.getArrival() == null) {
            throw new IllegalArgumentException("Flight arrival is null");
        }
        if (flight.getDeparture().after(flight.getArrival())){
            throw new IllegalArgumentException("Departure is after arrival");
        }

        Query query = null;
        if (flight.getId() != null) {
            query = em.createNamedQuery(Flight.QUERY_IS_STEWARD_AVAILABLE4FLIGHT_EXCLUSIVE);
            query.setParameter("flightId", flight.getId());
        } else {
            query = em.createNamedQuery(Flight.QUERY_IS_STEWARD_AVAILABLE4FLIGHT);
        }

        query.setParameter("stewardId", stewardId);
        query.setParameter("fromT", flight.getDeparture());
        query.setParameter("toT", flight.getArrival());

        Long count = (Long) query.getSingleResult();

        return count == 0;
    }
}
