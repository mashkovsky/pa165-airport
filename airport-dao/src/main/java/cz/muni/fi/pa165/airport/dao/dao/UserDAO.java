/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airport.dao.dao;

import cz.muni.fi.pa165.airport.dao.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Matej Chrenko
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
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("User password is null.");
        }

        em.persist(user);
    }

    @Override
    public void update(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("User id is null.");
        }
        if (user.getName() == null) {
            throw new IllegalArgumentException("User name is null.");
        }
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("User password is null.");
        }

        em.merge(user);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null");
        }
        User user = em.find(User.class, id);
        if (user == null) {
            throw new IllegalArgumentException("User with ID " + id + "does not exist");
        }

        user.setArchived(true);
    }

    @Override
    public User find(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null");
        }

        Query query = em.createQuery("SELECT p FROM User p WHERE p.archived = :archived AND p.id = :id");
        query.setParameter("archived", false);
        query.setParameter("id", id);

        List resultList = query.getResultList();

        return resultList.isEmpty() ? null : (User) resultList.get(0);
    }

    public User findByUsernameAndPassword(String username, String password) {
        if (username == null) {
            throw new IllegalArgumentException("Username is null");
        }

        if (password == null) {
            throw new IllegalArgumentException("Passwords is null");
        }

        Query query = em.createQuery("SELECT u FROM User u WHERE u.archived = :archived AND u.password = :password AND u.name = :username");
        query.setParameter("archived", false);
        query.setParameter("username", username);
        query.setParameter("password", password);

        List resultList = query.getResultList();

        return resultList.isEmpty() ? null : (User) resultList.get(0);
    }

    public User findByToken(String token) {
        if (token == null) {
            throw new IllegalArgumentException("Token is null");
        }

        Query query = em.createQuery("SELECT u FROM User u WHERE u.archived = :archived AND u.token = :token");
        query.setParameter("archived", false);
        query.setParameter("token", token);

        List resultList = query.getResultList();

        return resultList.isEmpty() ? null : (User) resultList.get(0);
    }

    @Override
    public List<User> getAll() {
        Query query = em.createQuery("SELECT f FROM User f WHERE f.archived = :archived  ORDER BY f.name ASC");
        query.setParameter("archived", false);
        return (List<User>) query.getResultList();
    }
}
