package cz.muni.fi.pa165.airport.ui.security;

import cz.muni.fi.pa165.airport.api.security.CustomRole;
import cz.muni.fi.pa165.airport.service.service.SecurityUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Provides custom authentication of users with given username and password hash
 *
 * @author Mariia Schevchenko
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {
    
    /** service for getting user details */
    private UserDetailsService userDetailsService;
    
    /** serves for encoding passwords */
    private PasswordEncoder passwordEncoder;

    
    /**
     * Sets <code>UserDetailsService</code> for getting user according 
     * username
     * @param userDetailsService 
     */
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Sets desirable password encoder
     * @param passwordEncoder 
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        if (passwordEncoder == null) {
            throw new IllegalStateException("No password encoder provided");
        }
        
        String username = String.valueOf(a.getPrincipal());
        String password = String.valueOf(a.getCredentials());
        
        SecurityUser userDetails;

        if (userDetailsService == null) {
            throw new IllegalArgumentException("User details service is null");
        }
         
        userDetails = (SecurityUser) userDetailsService.loadUserByUsername(username);
        
        if (!userDetails.getUsername().equals(username)) {
            throw new UsernameNotFoundException("No such username.");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }

        userDetails.addAuthority(CustomRole.LOGGED);
        if (userDetails.getUser().isPrivileged()) {
            System.out.println("User is privileged, adding role " + CustomRole.ADMIN);
            userDetails.addAuthority(CustomRole.ADMIN);
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails, 
                userDetails.getPassword(), 
                userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<? extends Object> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }    
}
