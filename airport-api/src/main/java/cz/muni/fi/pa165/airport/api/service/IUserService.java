package cz.muni.fi.pa165.airport.api.service;

import cz.muni.fi.pa165.airport.api.dto.UserDTO;

import java.util.List;

/**
 * @author Matej Chrenko
 */
public interface IUserService {
    /**
     * Create new user
     *
     * @param user user to create
     */
    void createUser(UserDTO user);
    
    /**
     * Update existing user identified by {@code id} from system
     *
     * @param user user to update
     * @throws IllegalArgumentException if {@code user.id} is {@code null}
     */
    void updateUser(UserDTO user);
    
    /**
     * Delete existing user identified by {@code id} from system
     *
     * @param userId user unique id
     * @throws IllegalArgumentException if {@code user.id} is {@code null}
     */
    void deleteUser(Long userId);
    
    /**
     * Get all users ordered by name
     *
     * @return list of all users or empty list if no users exist
     */
    List<UserDTO> getAllUsers();
    
    /**
     * Get user by {@code userId}
     *
     * @param userId user unique id
     * @return user or {@code null} if no such user exist
     * @throws IllegalArgumentException if {@code userId} is {@code null}
     */
    UserDTO getUser(Long userId);
}
