package cz.muni.fi.pa165.airport.dao.dao;

import cz.muni.fi.pa165.airport.dao.entity.Steward;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Mariia Shevchenko
 */
@Repository
public class StewardDAO implements IStewardDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void create(Steward steward) {
        if (steward == null) {
            throw new IllegalArgumentException("Steward is null");
        }
        if (steward.getId() != null) {
            throw new IllegalArgumentException("Steward id is not null.");
        }
        if (steward.getFirstName() == null) {
            throw new IllegalArgumentException("Steward first name is null.");
        }
        if (steward.getLastName() == null) {
            throw new IllegalArgumentException("Steward last name is null.");
        }

        em.persist(steward);
    }

    @Override
    public void update(Steward steward) {
        if (steward == null) {
            throw new IllegalArgumentException("Steward is null");
        }
        if (steward.getId() == null) {
            throw new IllegalArgumentException("Steward id is null.");
        }
        if (steward.getFirstName() == null) {
            throw new IllegalArgumentException("Steward first name is null.");
        }
        if (steward.getLastName() == null) {
            throw new IllegalArgumentException("Steward last name is null.");
        }

        em.merge(steward);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null");
        }

        Steward steward = em.find(Steward.class, id);
        if (steward == null) {
            throw new IllegalArgumentException("Steward with ID " + id + "does not exist");
        }

        steward.setArchived(true);
    }

    @Override
    public Steward find(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null");
        }

        Query query = em.createQuery("SELECT s FROM Steward s WHERE s.archived = :archived AND s.id = :id");
        query.setParameter("archived", false);
        query.setParameter("id", id);

        List resultList = query.getResultList();

        return resultList.isEmpty() ? null : (Steward) resultList.get(0);
    }

    @Override
    public List<Steward> getAll() {
        Query query = em.createQuery("SELECT s FROM Steward s WHERE s.archived = :archived ORDER BY s.lastName, s.firstName");
        query.setParameter("archived", false);
        return (List<Steward>) query.getResultList();
    }
}
