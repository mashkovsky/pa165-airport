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
     * @throws IllegalArgumentException if {@code destination.id} is not {@code null}
     */
    void createDestination(DestinationDTO destination);

    /**
     * Update existing destination
     *
     * @param destination destination to update
     * @throws IllegalArgumentException if {@code destination.id} is {@code null}
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
     * Get all destinations ordered by coutry, city
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
