package cz.muni.fi.pa165.airport.service.service;

import cz.muni.fi.pa165.airport.api.dto.UserDTO;
import cz.muni.fi.pa165.airport.api.service.IUserService;
import cz.muni.fi.pa165.airport.dao.dao.IUserDAO;
import cz.muni.fi.pa165.airport.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mariia Schevchenko
 */
@Service("userService")
@Transactional
public class UserService extends ConversionAware implements IUserService, UserDetailsService {

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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.getUserByEmail(email);


        if (user == null) {
            throw new UsernameNotFoundException("No such user with email " + email);
        }

        SecurityUser securityUser = new SecurityUser();
        securityUser.setUser(mapper.map(user, UserDTO.class));

        return securityUser;
    }
}
