package cz.muni.fi.pa165.airport.dao;

import cz.muni.fi.pa165.airport.entity.Flight;
import java.util.List;

/**
 * @author Zdenek Kanovsky
 */

public interface IFlightDAO {
    
    /**
     * Create new flight.
     *
     * @param flight flight to create
     * @throws IllegalArgumentException if flight is {@code null}
     *                                  or flight.id is not {@code null}
     *                                  or flight.departure, flight.arrival 
     *                                     flight.origin, flight.destination 
     *                                     flight.plane, flight.stewards
     *                                     is {@code null}
     */
    void create(Flight flight);

    /**
     * Update flight attributes.
     *
     * @param flight flight to update
     * @throws IllegalArgumentException if flight is {@code null}
     *                                  or flight.id is {@code null}
     *                                  or flight.departure, flight.arrival 
     *                                     flight.origin, flight.destination 
     *                                     flight.plane, flight.stewards
     *                                     is {@code null}
     */
    void update(Flight flight);

    /**
     * Delete flight record identified by {@code id}.
     *
     * @param id flight unique identifier
     * @throws IllegalArgumentException if {@code id} is {@code null}
     * @throws cz.muni.fi.pa165.airport.exception.RepositoryException
     *                                  if flight with given {@code id} does not exist
     */
    
    void delete(Long id);

    /**
     * Find flight identified with {@code id}.
     *
     * @param id flight unique identifier
     * @return flight instance or {@code null} if flight with given {@code id} does not exist
     * @throws IllegalArgumentException if {@code id} is {@code null}
     */
    Flight find(Long id);

    /**
     * Get all flights from storage ordered by departure.
     *
     * @return all flights or empty list.
     */
    List<Flight> getAll();

}

