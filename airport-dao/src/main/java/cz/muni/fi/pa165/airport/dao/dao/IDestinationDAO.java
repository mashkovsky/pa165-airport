package cz.muni.fi.pa165.airport.dao.dao;

import cz.muni.fi.pa165.airport.dao.entity.Destination;

import java.util.List;

/**
 * @author Zdenek Kanovsky
 */
public interface IDestinationDAO {
    
    /**
     * Create new destination.
     *
     * @param destination destination to create
     * @throws IllegalArgumentException if destination is {@code null}
     *                                  or destination.id is not {@code null}
     *                                  or destination.city is {@code null} 
     *                                  or destination.country is {@code null}
     */
    void create(Destination destination);
    
    /**
     * Update destination.
     *
     * @param destination destination to update
     * @throws IllegalArgumentException if destination is {@code null}
     *                                  or destination.id is {@code null}
     *                                  or destination.city is {@code null} 
     *                                  or destination.country is {@code null}
     */
    void update(Destination destination);
    
    /**
     * Archives entity identified by {@code id}.
     *
     * @param id id of destination to delete
     * @throws IllegalArgumentException if {@code id} is {@code null}
     *                                  or destination with given {@code id} does not exist
     */    
    void delete(Long id);
    
    /**
     * Find destination identified with {@code id}.
     *
     * @param id destination unique identifier
     * @return destination instance or {@code null} if destination with given {@code id} does not exist
     * @throws IllegalArgumentException if {@code id} is {@code null}
     */
    Destination find(Long id);
    
    /**
     * Get all destinations from storage ordered by country, city.
     *
     * @return all destinations or empty list.
     */
    List<Destination> getAll();
    
    /**
     * Check if destination is not used in any flight ever.
     * 
     * @param destinationId id of destination
     * @return {@code true} if destination is not occupied, {@code false} otherwise
     * @throws IllegalArgumentException if {@code destinationId} is {@code null}
     */
    boolean isNotUsedInFlights(Long destinationId);
}
