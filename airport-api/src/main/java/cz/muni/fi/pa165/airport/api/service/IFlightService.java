package cz.muni.fi.pa165.airport.api.service;

import cz.muni.fi.pa165.airport.api.dto.FlightDetailDTO;
import cz.muni.fi.pa165.airport.api.dto.FlightMinimalDTO;

import java.util.List;

/**
 * @author Mariia Shevchenko
 */
public interface IFlightService {

    /**
     * Create new flight
     *
     * @param flight flight to create
     * @throws IllegalStateException if plane or steward is not available for flight duration
     *                               if departure date is in future according to arrival (past arrival)
     */
    void createFlight(FlightDetailDTO flight);

    /**
     * Update existing flight
     *
     * @param flight flight to create
     * @throws IllegalStateException if plane or steward is not available for flight duration
     *                               if departure date is in future according to arrival (past arrival)
     * @throws IllegalArgumentException if {@code flight.id} is {@code null}
     */
    void updateFlight(FlightDetailDTO flight);

    /**
     * Delete flight identified by {@code id} from system.
     *
     * @param flightId flight unique ID
     * @throws IllegalArgumentException if {@code flightId} is {@code null}
     */
    void deleteFlight(Long flightId);

    /**
     * Get all flights ordered by departure date
     *
     * @return list of all flights or empty list if no flight exist
     */
    List<FlightMinimalDTO> getAllFlights();

    /**
     * Get flight detail by {@code flightId}
     *
     * @param flightId unique flight ID
     * @return flight detail or {@code null} if no such flight exist
     * @throws IllegalArgumentException if {@code flightId} is {@code null}
     */
    FlightDetailDTO getFlightDetail(Long flightId);
}
