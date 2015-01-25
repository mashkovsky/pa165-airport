package cz.muni.fi.pa165.airport.service.service;

import cz.muni.fi.pa165.airport.api.dto.UserDTO;
import cz.muni.fi.pa165.airport.api.service.IUserService;
import cz.muni.fi.pa165.airport.dao.dao.IUserDAO;
import cz.muni.fi.pa165.airport.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Methods of this class can throw subclasses of org.springframework.dao.DataAccessException if error occurs on persistence layer
 * 
 * @author Jan Jilek
 */
@Service
@Transactional
public class UserService extends ConversionAware implements IUserService {
    
    @Autowired
    private IUserDAO userDAO;
    
    @Override
    public void createUser(UserDTO user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User ID is not null");
        }

        User entity = mapper.map(user, User.class);
        userDAO.create(entity);
        user.setId(entity.getId());
        
    }

    @Override
    public void updateUser(UserDTO user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User ID is null");
        }
        User entity = mapper.map(user, User.class);
        userDAO.update(entity);
    }
    
    @Override
    public void deleteUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID is null");
        }
        userDAO.delete(userId);
    }
    
    @Override
    public List<UserDTO> getAllUsers() {
        return map(userDAO.getAll(), UserDTO.class);
    }

    @Override
    public UserDTO getUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID is null");
        }

        User user = userDAO.find(userId);
        return user == null ? null : mapper.map(user, UserDTO.class);
    }
        
    
}
