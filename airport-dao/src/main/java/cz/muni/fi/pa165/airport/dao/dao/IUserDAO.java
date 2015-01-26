package cz.muni.fi.pa165.airport.dao.dao;

import cz.muni.fi.pa165.airport.dao.entity.User;

/**
 * @author Mariia Schevchenko
 */
public interface IUserDAO {

    /**
     * Create user
     *
     * @param user user to create
     */
    void create(User user);

    /**
     * Find user by email
     *
     * @param email
     * @return user or {@code null}
     */
    User getUserByEmail(String email);

}
