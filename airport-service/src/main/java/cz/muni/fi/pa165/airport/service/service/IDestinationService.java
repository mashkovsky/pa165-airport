package cz.muni.fi.pa165.airport.service.service;

import cz.muni.fi.pa165.airport.service.service.dto.DestinationDTO;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Zdenek Kanovsky
 */
@Transactional
public interface IDestinationService {
    
    /**
     * Create new destination
     *
     * @param destination destination to create
     * @throws IllegalStateException if plane or steward is not available for flight duration
     *                               if departure date is in future according to arrival (past arrival)
     */
    void createDestination(DestinationDTO destination);

    /**
     * Update existing destination
     *
     * @param destination destination to update
     * @throws IllegalStateException if plane or steward is not available for flight duration
     *                               if departure date is in future according to arrival (past arrival)
     * @throws IllegalArgumentException if {@code flight.id} is {@code null}
     */
    void updateDestination(DestinationDTO destination);

    /**
     * Delete destination identified by {@code id} from system.
     *
     * @param destinationId destination unique ID
     * @throws IllegalArgumentException if {@code destinationId} is {@code null}
     */
    void deleteDestination(Long destinationId);

    /**
     * Get all destinations ordered by name
     *
     * @return list of all destinations or empty list if no destination exist
     */
    List<DestinationDTO> getAllDestinations();

    /**
     * Get destination by {@code destinationId}
     *
     * @param destinationId unique destination ID
     * @return destination or {@code null} if no such destination exist
     * @throws IllegalArgumentException if {@code destinationId} is {@code null}
     */
    DestinationDTO getDestination(Long destinationId);
}
