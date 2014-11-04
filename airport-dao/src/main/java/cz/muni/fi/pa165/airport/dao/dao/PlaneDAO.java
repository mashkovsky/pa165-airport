/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airport.dao.dao;

import cz.muni.fi.pa165.airport.dao.entity.Plane;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Matej Chrenko
 */
@Repository
public class PlaneDAO implements IPlaneDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Plane plane) {
        if (plane == null) {
            throw new IllegalArgumentException("Plane is null");
        }
        if (plane.getId() != null) {
            throw new IllegalArgumentException("Plane id is not null.");
        }
        if (plane.getName() == null) {
            throw new IllegalArgumentException("Plane name is null.");
        }
        if (plane.getCapacity() == null) {
            throw new IllegalArgumentException("Plane capacity is null.");
        }
        if (plane.getCapacity() < 1) {
            throw new IllegalArgumentException("Capacity can not be smaller than 1");
        }
        if (plane.getType() == null) {
            throw new IllegalArgumentException("Plane type is null.");
        }

        em.persist(plane);
    }

    @Override
    public void update(Plane plane) {
        if (plane == null) {
            throw new IllegalArgumentException("Plane is null");
        }
        if (plane.getId() == null) {
            throw new IllegalArgumentException("Plane id is null.");
        }
        if (plane.getName() == null) {
            throw new IllegalArgumentException("Plane name is null.");
        }
        if (plane.getCapacity() == null) {
            throw new IllegalArgumentException("Plane capacity is null.");
        }
        if (plane.getCapacity() < 1) {
            throw new IllegalArgumentException("Capacity can not be smaller than zero");
        }
        if (plane.getType() == null) {
            throw new IllegalArgumentException("Plane type is null.");
        }

        em.merge(plane);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null");
        }
        Plane plane = em.find(Plane.class, id);
        if (plane == null) {
            throw new IllegalArgumentException("Plane with ID " + id + "does not exist");
        }

        em.remove(plane);
    }

    @Override
    public Plane find(Long id) {
        return em.find(Plane.class, id);
    }

    @Override
    public List<Plane> getAll() {
        Query query = em.createQuery("SELECT f FROM Plane f ORDER BY f.name ASC");
        return (List<Plane>) query.getResultList();
    }
}
