package cz.muni.fi.pa165.airport.dao;

import cz.muni.fi.pa165.airport.entity.Destination;
import cz.muni.fi.pa165.airport.entity.Steward;

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
