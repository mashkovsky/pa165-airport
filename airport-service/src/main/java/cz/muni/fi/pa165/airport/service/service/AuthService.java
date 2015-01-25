package cz.muni.fi.pa165.airport.service.service;

import cz.muni.fi.pa165.airport.api.dto.UserDTO;
import cz.muni.fi.pa165.airport.api.service.IUserService;
import cz.muni.fi.pa165.airport.dao.dao.IUserDAO;
import cz.muni.fi.pa165.airport.dao.entity.Steward;
import cz.muni.fi.pa165.airport.dao.entity.User;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomStringUtils.random;

/**
 * Methods of this class can throw subclasses of
 * org.springframework.dao.DataAccessException if error occurs on persistence
 * layer
 *
 * @author Matej Chrenko
 */
@Service
@Transactional
public class AuthService extends ConversionAware {

    @Autowired
    private IUserDAO userDAO;

    public String login(String username, String password) {
        User user = new User();
        user = userDAO.findByUsernameAndPassword(username, password);
        if (user != null) {
            String token = username + UUID.randomUUID().toString();
            user.setToken(token);
            return token;
        } else {
            return null;
        }
    }

}
