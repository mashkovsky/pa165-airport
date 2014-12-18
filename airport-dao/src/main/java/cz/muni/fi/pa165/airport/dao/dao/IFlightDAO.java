package cz.muni.fi.pa165.airport.dao.dao;

import cz.muni.fi.pa165.airport.dao.entity.Flight;

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
     * Archives entity identified by {@code id}.
     *
     * @param id flight unique identifier
     * @throws IllegalArgumentException if {@code id} is {@code null}
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

    /**
     * Check if plane is not occupied by another flight at time {@code from} - {@code to}
     *
     * @param planeId plane unique ID which is checked for availability
     * @param flight flight for which check is done
     * @return {@code true} if plane is not occupied, {@code false} otherwise
     * @throws IllegalArgumentException if any of argument is {@code null} or {@code from} is after {@code to}
     */
    boolean isPlaneAvailableForFlight(Long planeId, Flight flight);

    /**
     * Check if steward is not occupied by another flight at time {@code from} - {@code to}
     *
     * @param stewardId steward unique ID which is checked for availability
     * @param flight flight for which check is done
     * @return {@code true} if steward is not occupied, {@code false} otherwise
     * @throws IllegalArgumentException if any of argument is {@code null} or {@code from} is after {@code to}
     */
    boolean isStewardAvailableForFlight(Long stewardId, Flight flight);
}

