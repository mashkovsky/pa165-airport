package cz.muni.fi.pa165.airport.dao;

import cz.muni.fi.pa165.airport.entity.Destination;
import cz.muni.fi.pa165.airport.entity.Flight;
import cz.muni.fi.pa165.airport.entity.Plane;
import cz.muni.fi.pa165.airport.entity.Steward;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author Mariia Shevchenko
 */
public class FlightDAOTest extends BaseDAOTest {

    @Autowired
    private IFlightDAO flightDAO;

    @Autowired
    private IDestinationDAO destinationDAO;

    @Autowired
    private IPlaneDAO planeDAO;

    @Autowired
    private IStewardDAO stewardDAO;


    private Flight flight;
    private Destination origin;
    private Destination destination;
    private Plane plane;
    private Steward steward1;
    private Steward steward2;
    private List<Steward> stewards;

    @Before
    public void setUp() {
        origin = new Destination();
        origin.setCountry("CZ");
        origin.setCity("Prague");

        destination = new Destination();
        destination.setCountry("FR");
        destination.setCity("Paris");

        destinationDAO.create(origin);
        destinationDAO.create(destination);

        plane = new Plane();
        plane.setName("Airbus");
        plane.setType("A180");
        plane.setCapacity(300);

        planeDAO.create(plane);

        steward1 = new Steward();
        steward1.setFirstName("Homer");
        steward1.setLastName("Simpson");

        steward2 = new Steward();
        steward2.setFirstName("Marge");
        steward2.setLastName("Simpson");

        stewardDAO.create(steward1);
        stewardDAO.create(steward2);

        stewards = new ArrayList<Steward>();
        stewards.add(steward1);
        stewards.add(steward2);

        flight = new Flight();
        flight.setArrival(new Date(1413139340));
        flight.setDeparture(new Date(1413139341));
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setPlane(plane);
        flight.setStewards(stewards);
    }

    @Test
    private void test() {

    }

}
