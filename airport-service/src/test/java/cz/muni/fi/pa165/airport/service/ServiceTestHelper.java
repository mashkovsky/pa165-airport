package cz.muni.fi.pa165.airport.service;

import cz.muni.fi.pa165.airport.dao.entity.Destination;
import cz.muni.fi.pa165.airport.dao.entity.Flight;
import cz.muni.fi.pa165.airport.dao.entity.Plane;
import cz.muni.fi.pa165.airport.dao.entity.Steward;

import java.util.Date;
import java.util.List;

/**
 * @author Mariia Schevchenko
 */
public final class ServiceTestHelper {

    private ServiceTestHelper() {
        // According to Bloch, Effective Java
        throw new IllegalStateException("Do not initialize helper class");
    }

    public static Plane preparePlane(Long id, int capacity, String name, String type) {
        Plane plane = new Plane();
        plane.setId(id);
        plane.setCapacity(capacity);
        plane.setName(name);
        plane.setType(type);

        return plane;
    }

    public static Destination prepareDestination(Long id, String country, String city) {
        Destination destination = new Destination();
        destination.setId(id);
        destination.setCountry(country);
        destination.setCity(city);

        return destination;
    }

    public static Steward prepareSteward(Long id, String firstName, String lastName) {
        Steward steward = new Steward();
        steward.setId(id);
        steward.setFirstName(firstName);
        steward.setLastName(lastName);

        return steward;
    }

    public static Flight prepareFlight(Long id, Date departure, Date arrival, Plane plane, Destination origin, Destination destination, List<Steward> stewards) {
        Flight flight = new Flight();
        flight.setId(id);
        flight.setDeparture(departure);
        flight.setArrival(arrival);
        flight.setPlane(plane);
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setStewards(stewards);

        return flight;
    }


}
