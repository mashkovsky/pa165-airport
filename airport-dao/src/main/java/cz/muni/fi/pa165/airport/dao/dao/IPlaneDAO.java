package cz.muni.fi.pa165.airport.dao.dao;

import cz.muni.fi.pa165.airport.dao.entity.Plane;

import java.util.List;

/**
 * @author Matej Chrenko
 */
public interface IPlaneDAO {

    /**
     * Create new plane.
     *
     * @param plane plane to create
     * @throws IllegalArgumentException if plane is {@code null} or plane.id is
     * not {@code null} or plane.name, plane.type, plane.capacity is
     * {@code null}
     */
    void create(Plane plane);

    /**
     * Update plane attributes.
     *
     * @param plane plane to update
     * @throws IllegalArgumentException if plane is {@code null} or plane.id is
     * {@code null} or plane.name, plane.type, plane.capacity is {@code null}
     */
    void update(Plane plane);

    /**
     * Delete plane record identified by {@code id}.
     *
     * @param id plane unique identifier
     * @throws IllegalArgumentException if {@code id} is {@code null}
     *                                  if plane with given {@code id} does not exist
     */
    void delete(Long id);

    /**
     * Find plane identified with {@code id}.
     *
     * @param id plane unique identifier
     * @return plane instance or {@code null} if plane with given {@code id}
     * does not exist
     * @throws IllegalArgumentException if {@code id} is {@code null}
     */
    Plane find(Long id);

    /**
     * Get all planes from storage ordered by last name then first name.
     *
     * @return all planes or empty list.
     */
    List<Plane> getAll();

}
