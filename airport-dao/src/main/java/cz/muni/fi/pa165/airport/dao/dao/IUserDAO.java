package cz.muni.fi.pa165.airport.dao.dao;

import cz.muni.fi.pa165.airport.dao.entity.User;

import java.util.List;

/**
 * @author Matej Chrenko
 */
public interface IUserDAO {

    /**
     * Create new user.
     *
     * @param user user to create
     * @throws IllegalArgumentException if user is {@code null} or user.id is
     * not {@code null} or user.name, user.type, user.capacity is
     * {@code null}
     */
    void create(User user);

    /**
     * Update user attributes.
     *
     * @param user user to update
     * @throws IllegalArgumentException if user is {@code null} or user.id is
     * {@code null} or user.name, user.type, user.capacity is {@code null}
     */
    void update(User user);

    /**
     * Archives entity identified by {@code id}.
     *
     * @param id user unique identifier
     * @throws IllegalArgumentException if {@code id} is {@code null}
     *                                  if user with given {@code id} does not exist
     */
    void delete(Long id);

    /**
     * Find user identified with {@code id}.
     *
     * @param id user unique identifier
     * @return user instance or {@code null} if user with given {@code id}
     * does not exist
     * @throws IllegalArgumentException if {@code id} is {@code null}
     */
    User find(Long id);

    /**
     * Get all users from storage ordered by last name then first name.
     *
     * @return all users or empty list.
     */
    List<User> getAll();

}
