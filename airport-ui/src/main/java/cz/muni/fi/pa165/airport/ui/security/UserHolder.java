package cz.muni.fi.pa165.airport.ui.security;

import cz.muni.fi.pa165.airport.service.service.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Provides user principal from security context's session.
 *
 * @author Mariia Schevchenko
 */
public class UserHolder {
    
    /**
     * Provides user principal from security context's session
     * @return 
     */
    public static SecurityUser getUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth == null) ? null : (SecurityUser) auth.getPrincipal();
    }
}
