package cz.muni.fi.pa165.airport.api.security;

/**
 * System user role {@code GrantedAuthority} constants
 *
 * @author Mariia Schevchenko
 */
public class CustomRole {

    /** Anonymous user */
    public static final String ANONYMOUS = "ANONYMOUS";

    /** Logged user */
    public static final String LOGGED = "LOGGED";

    /** Logged user with administration privileges */
    public static final String ADMIN = "ADMIN";

}
