package cz.muni.fi.pa165.airport.dao;

import cz.muni.fi.pa165.airport.entity.Steward;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Mariia Shevchenko
 */
@Repository
@Transactional
public class StewardDAO implements IStewardDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void create(Steward steward) {

    }

    @Override
    public void update(Steward steward) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Steward find(Long id) {
        return null;
    }

    @Override
    public List<Steward> getAll() {
        return null;
    }
}
