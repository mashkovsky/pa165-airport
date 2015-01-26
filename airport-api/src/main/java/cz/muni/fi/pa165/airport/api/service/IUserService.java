package cz.muni.fi.pa165.airport.api.service;

import cz.muni.fi.pa165.airport.api.dto.UserDTO;

/**
 * @author Mariia Schevchenko
 */
public interface IUserService {

    /**
     * Create new user
     *
     * @param user user to create
     */
    void createUser(UserDTO user);
}
