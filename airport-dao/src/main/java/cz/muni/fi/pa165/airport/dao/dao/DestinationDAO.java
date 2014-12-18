package cz.muni.fi.pa165.airport.dao.dao;

import cz.muni.fi.pa165.airport.dao.entity.Destination;
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

        destination.setArchived(true);
    }

    @Override
    public Destination find(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null");
        }

        Query query = em.createQuery("SELECT d FROM Destination d WHERE d.archived = :archived AND d.id = :id");
        query.setParameter("archived", false);
        query.setParameter("id", id);

        List resultList = query.getResultList();

        return resultList.isEmpty() ? null : (Destination) resultList.get(0);
    }

    @Override
    public List<Destination> getAll() {
        Query query = em.createQuery("SELECT d FROM Destination d WHERE d.archived = :archived  ORDER BY d.country, d.city");
        query.setParameter("archived", false);
        return (List<Destination>) query.getResultList();
    }
    
    @Override
    public boolean isNotUsedInFlights(Long destinationId) {
        if (destinationId == null) {
            throw new IllegalArgumentException("Destination ID is null");
        }

        Query query = em.createNamedQuery(Destination.QUERY_IS_NOT_USED_IN_FLIGHTS);

        query.setParameter("destinationId", destinationId);
        Long count = (Long) query.getSingleResult();

        return count == 0;
    }
}
