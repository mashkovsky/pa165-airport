package cz.muni.fi.pa165.airport.exception;

/**
 * Exception thrown whenever any of storage exception occurs. E.G. when removing entity which
 * is linked to another etc.
 *
 * @author Mariia Shevchenko
 */
public class RepositoryException extends RuntimeException {

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
