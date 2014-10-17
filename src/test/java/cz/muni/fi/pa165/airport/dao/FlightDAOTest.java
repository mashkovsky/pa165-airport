package cz.muni.fi.pa165.airport.dao;

import cz.muni.fi.pa165.airport.entity.Destination;
import cz.muni.fi.pa165.airport.entity.Flight;
import cz.muni.fi.pa165.airport.entity.Plane;
import cz.muni.fi.pa165.airport.entity.Steward;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
    private Destination originSecond;
    private Destination destination;
    private Destination destinationSecond;
    private Plane plane;
    private Plane planeSecond;
    private Steward steward1;
    private Steward steward1Second;
    private Steward steward2;
    private Steward steward2Second;

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

        List<Steward> stewards = new ArrayList<Steward>();
        stewards.add(steward1);
        stewards.add(steward2);

        flight = new Flight();
        flight.setArrival(new Date(1413139341));
        flight.setDeparture(new Date(1413139340));
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setPlane(plane);
        flight.setStewards(stewards);
    }

    private Flight prepareSecondFlight() {
        originSecond = new Destination();
        originSecond.setCountry("PL");
        originSecond.setCity("Katowice");

        destinationSecond = new Destination();
        destinationSecond.setCountry("GB");
        destinationSecond.setCity("London");

        destinationDAO.create(originSecond);
        destinationDAO.create(destinationSecond);

        planeSecond = new Plane();
        planeSecond.setName("Antonov");
        planeSecond.setType("AN");
        planeSecond.setCapacity(600);

        planeDAO.create(planeSecond);

        steward1Second = new Steward();
        steward1Second.setFirstName("Bart");
        steward1Second.setLastName("Simpson");

        steward2Second = new Steward();
        steward2Second.setFirstName("Lisa");
        steward2Second.setLastName("Simpson");

        stewardDAO.create(steward1Second);
        stewardDAO.create(steward2Second);

        List<Steward> stewardsSecond = new ArrayList<Steward>();
        stewardsSecond.add(steward1Second);
        stewardsSecond.add(steward2Second);

        Flight second = new Flight();
        second.setArrival(new Date(1413139350));
        second.setDeparture(new Date(1413139340));
        second.setOrigin(originSecond);
        second.setDestination(destinationSecond);
        second.setPlane(planeSecond);
        second.setStewards(stewardsSecond);

        return second;
    }

    /*
     * Create
     */
    
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNullOrigin() {
        flight.setOrigin(null);
        flightDAO.create(flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNullDestination() {
        flight.setDestination(null);
        flightDAO.create(flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNullArrival() {
        flight.setArrival(null);
        flightDAO.create(flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNullDeparture() {
        flight.setDeparture(null);
        flightDAO.create(flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateArrivalBeforeDeparture() {
        flight.setDeparture(new Date(1413139000));
        flight.setArrival(new Date(1413138000));
        flightDAO.create(flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNullPlane() {
        flight.setPlane(null);
        flightDAO.create(flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNullStewards() {
        flight.setStewards(null);
        flightDAO.create(flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNoStewards() {
        flight.setStewards(new ArrayList<Steward>());
        flightDAO.create(flight);
    }

    @Test
    public void testCreate() {
        flightDAO.create(flight);

        assertNotNull(flight.getId());

        Flight fromDb = flightDAO.find(flight.getId());
        assertEquals(plane, fromDb.getPlane());
        assertEquals(origin, fromDb.getOrigin());
        assertEquals(destination, fromDb.getDestination());
        assertEquals(new Date(1413139340), fromDb.getDeparture());
        assertEquals(new Date(1413139341), fromDb.getArrival());
        assertEquals(2, fromDb.getStewards().size());
        assertTrue(fromDb.getStewards().contains(steward1));
        assertTrue(fromDb.getStewards().contains(steward2));
    }
    
    
    /*
     * Update
     */

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdateNullOrigin() {
        flightDAO.create(flight);

        flight.setOrigin(null);
        flightDAO.update(flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdateNullDestination() {
        flightDAO.create(flight);

        flight.setDestination(null);
        flightDAO.update(flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdateNullArrival() {
        flightDAO.create(flight);

        flight.setArrival(null);
        flightDAO.update(flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdateNullDeparture() {
        flightDAO.create(flight);

        flight.setDeparture(null);
        flightDAO.update(flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdateArrivalBeforeDeparture() {
        flightDAO.create(flight);

        flight.setDeparture(new Date(1413139000));
        flight.setArrival(new Date(1413138000));
        flightDAO.update(flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdateNullPlane() {
        flightDAO.create(flight);

        flight.setPlane(null);
        flightDAO.update(flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdateNullStewards() {
        flightDAO.create(flight);

        flight.setStewards(null);
        flightDAO.update(flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdateNoStewards() {
        flightDAO.create(flight);

        flight.setStewards(new ArrayList<Steward>());
        flightDAO.update(flight);
    }

    @Test
    public void testUpdate() {
        flightDAO.create(flight);

        Flight second = prepareSecondFlight();
        second.setId(flight.getId());

        flightDAO.update(second);

        Flight fromDb = flightDAO.find(flight.getId());
        assertEquals(planeSecond, fromDb.getPlane());
        assertEquals(originSecond, fromDb.getOrigin());
        assertEquals(destinationSecond, fromDb.getDestination());
        assertEquals(new Date(1413139340), fromDb.getDeparture());
        assertEquals(new Date(1413139350), fromDb.getArrival());
        assertEquals(2, fromDb.getStewards().size());
        assertTrue(fromDb.getStewards().contains(steward1Second));
        assertTrue(fromDb.getStewards().contains(steward2Second));
    }

    /*
     * Delete
     */

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testDeleteNotExistentId() {
        flightDAO.delete(1L);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testDeleteNullId() {
        flightDAO.delete(null);
    }

    @Test
    public void testDelete() {

        Flight second = prepareSecondFlight();

        flightDAO.create(flight);
        flightDAO.create(second);

        flightDAO.delete(flight.getId());
        assertNull(flightDAO.find(flight.getId()));
        assertEquals(second, flightDAO.find(second.getId()));
    }

    /*
     * Find
     */

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testFindNullId() {
        flightDAO.find(null);
    }

    @Test
    public void testFindNotExistentId() {
        assertNull(flightDAO.find(1L));
    }

    @Test
    public void testFind() {
        Flight second = prepareSecondFlight();

        flightDAO.create(flight);
        flightDAO.create(second);

        assertEquals(flight, flightDAO.find(flight.getId()));
        assertEquals(second, flightDAO.find(second.getId()));
    }

    /*
     * Get all
     */

    @Test
    public void testGetAll() {
        Flight second = prepareSecondFlight();

        flightDAO.create(flight);
        flightDAO.create(second);

        List<Flight> flights = flightDAO.getAll();
        assertEquals(2, flights.size());
        assertTrue(flights.contains(flight));
        assertTrue(flights.contains(second));
    }
}
