package cz.muni.fi.pa165.airport.dao.dao;

import cz.muni.fi.pa165.airport.dao.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Mariia Schevchenko
 */
@Repository
public class UserDAO implements IUserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }
        if (user.getId() != null) {
            throw new IllegalArgumentException("User id is not null.");
        }
        if (user.getName() == null) {
            throw new IllegalArgumentException("User name is null.");
        }
        if (user.getEmail() == null) {
            throw new IllegalArgumentException("User email is null.");
        }
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("User password name is null.");
        }

        em.persist(user);
    }

    @Override
    public User getUserByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email is null");
        }

        Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :email");
        query.setParameter("email", email);

        List resultList = query.getResultList();
        return resultList.isEmpty() ? null : (User) resultList.get(0);
    }
}
