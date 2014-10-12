package cz.muni.fi.pa165.airport.dao;

import cz.muni.fi.pa165.airport.entity.Steward;

import java.util.List;

/**
 * @author Mariia Shevchenko
 */
public interface IStewardDAO {

    /**
     * Create new steward.
     *
     * @param steward steward to create
     * @throws IllegalArgumentException if steward is {@code null}
     *                                  or steward.id is not {@code null}
     *                                  or steward.firstName, steward.lastName is {@code null}
     */
    void create(Steward steward);

    /**
     * Update steward attributes.
     *
     * @param steward steward to update
     * @throws IllegalArgumentException if steward is {@code null}
     *                                  or steward.id is {@code null}
     *                                  or steward.firstName, steward.lastName is {@code null}
     */
    void update(Steward steward);

    /**
     * Delete steward record identified by {@code id}.
     *
     * @param id steward unique identifier
     * @throws IllegalArgumentException if {@code id} is {@code null}
     *                                  if steward with given {@code id} does not exist
     */
    void delete(Long id);

    /**
     * Find steward identified with {@code id}.
     *
     * @param id steward unique identifier
     * @return steward instance or {@code null} if steward with given {@code id} does not exist
     * @throws IllegalArgumentException if {@code id} is {@code null}
     */
    Steward find(Long id);

    /**
     * Get all stewards from storage ordered by last name then first name.
     *
     * @return all stewards or empty list.
     */
    List<Steward> getAll();

}
