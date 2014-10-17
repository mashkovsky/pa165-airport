package cz.muni.fi.pa165.airport.dao;

import cz.muni.fi.pa165.airport.entity.Destination;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Zdenek Kanovsky
 */
@Repository
public class DestinationDAO implements IDestinationDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void create(Destination destination) {
        if (destination == null) {
            throw new IllegalArgumentException("Destination is null");
        }
        if (destination.getId() != null) {
            throw new IllegalArgumentException("Destination id is not null.");
        }
        if (destination.getCity() == null) {
            throw new IllegalArgumentException("Destination city is null.");
        }
        if (destination.getCountry() == null) {
            throw new IllegalArgumentException("Destination country is null.");
        }

        em.persist(destination);
    }

    @Override
    public void update(Destination destination) {
        if (destination == null) {
            throw new IllegalArgumentException("Destination is null");
        }
        if (destination.getId() == null) {
            throw new IllegalArgumentException("Destination id is null.");
        }
        if (destination.getCity() == null) {
            throw new IllegalArgumentException("Destination city is null.");
        }
        if (destination.getCountry() == null) {
            throw new IllegalArgumentException("Destination country is null.");
        }

        em.merge(destination);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null");
        }

        Destination destination = find(id);
        if (destination == null) {
            throw new IllegalArgumentException("Destination with ID " + id + "does not exist");
        }

        em.remove(destination);
    }

    @Override
    public Destination find(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null");
        }

        return em.find(Destination.class, id);
    }

    @Override
    public List<Destination> getAll() {
        Query query = em.createQuery("SELECT d FROM Destination d ORDER BY d.country, d.city");
        return (List<Destination>) query.getResultList();
    }
}
