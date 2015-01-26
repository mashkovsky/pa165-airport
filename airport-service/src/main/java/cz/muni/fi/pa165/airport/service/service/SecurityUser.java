package cz.muni.fi.pa165.airport.service.service;

import cz.muni.fi.pa165.airport.api.dto.UserDTO;
import cz.muni.fi.pa165.airport.api.security.CustomRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Mariia Schevchenko
 */
public class SecurityUser implements UserDetails, Serializable {

    /** Wrapped {@code UserDTO} object */
    private UserDTO user = new UserDTO();

    /** Authorities granted to user */
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();


    public SecurityUser() {
        initAnonymousUser();
    }

    /**
     * Sets default values of username and authority for anonymous user
     */
    private void initAnonymousUser() {
        setUsername("anonymous");
        addAuthority(CustomRole.ANONYMOUS);
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void addAuthority(String role) {
        this.authorities.add(new SimpleGrantedAuthority(role));
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    public void setPassword(String password) {
        user.setPassword(password);
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    public void setUsername(String email) {
        user.setEmail(email);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
