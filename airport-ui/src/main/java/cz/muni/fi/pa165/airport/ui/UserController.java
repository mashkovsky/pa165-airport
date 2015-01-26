package cz.muni.fi.pa165.airport.ui;

import cz.muni.fi.pa165.airport.api.dto.UserDTO;
import cz.muni.fi.pa165.airport.api.security.CustomRole;
import cz.muni.fi.pa165.airport.service.service.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mariia Schevchenko
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("loggedUser")
    private SecurityUser user;

    @PreAuthorize("hasRole('" + CustomRole.LOGGED + "')")
    @RequestMapping
    public UserDTO create() {
        return user.getUser();
    }

}
