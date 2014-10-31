package cz.muni.fi.pa165.airport.dao.dao;

import cz.muni.fi.pa165.airport.dao.entity.Destination;

import java.util.List;

/**
 * @author Zdenek Kanovsky
 */
public interface IDestinationDAO {

    void create(Destination destination);
    void update(Destination destination);
    void delete(Long id);
    Destination find(Long id);
    List<Destination> getAll();

}
