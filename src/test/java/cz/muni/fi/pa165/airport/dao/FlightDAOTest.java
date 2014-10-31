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
import static org.junit.Assert.assertFalse;
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
        flight.setDeparture(new Date(1413139340));
        flight.setArrival(new Date(1413139350));
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
        assertEquals(new Date(1413139350), fromDb.getArrival());
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
    
    /*
     * Plane is available for flight
     */

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testIsPlaneAvailableNullPlaneId() {
        flightDAO.isPlaneAvailableForFlight(null, flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testIsPlaneAvailableNullFlight() {
        flightDAO.isPlaneAvailableForFlight(plane.getId(), null);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testIsPlaneAvailableNullDeparture() {
        flight.setDeparture(null);
        flightDAO.isPlaneAvailableForFlight(plane.getId(), flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testIsPlaneAvailableNullArrival() {
        flight.setArrival(null);
        flightDAO.isPlaneAvailableForFlight(plane.getId(), flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testIsPlaneAvailableSwappedDates() {
        flight.setDeparture(new Date(1413139341));
        flight.setArrival(new Date(1413139340));
        flightDAO.isPlaneAvailableForFlight(plane.getId(), flight);
    }

    @Test
    public void testIsPlaneAvailableArrivalInInterval() {
        flightDAO.create(flight);

        Flight secondFlight = prepareSecondFlight();
        secondFlight.setDeparture(new Date(1413139339));
        secondFlight.setArrival(new Date(1413139340));

        assertFalse(flightDAO.isPlaneAvailableForFlight(plane.getId(), secondFlight));

        secondFlight.setDeparture(new Date(1413139339));
        secondFlight.setArrival(new Date(1413139341));
        assertFalse(flightDAO.isPlaneAvailableForFlight(plane.getId(), secondFlight));
    }

    @Test
    public void testIsPlaneAvailableBothInInterval() {
        flightDAO.create(flight);

        Flight secondFlight = prepareSecondFlight();
        secondFlight.setDeparture(new Date(1413139341));
        secondFlight.setArrival(new Date(1413139342));

        assertFalse(flightDAO.isPlaneAvailableForFlight(plane.getId(), secondFlight));
    }

    @Test
    public void testIsPlaneAvailableDepartureInInterval() {
        flightDAO.create(flight);

        Flight secondFlight = prepareSecondFlight();
        secondFlight.setDeparture(new Date(1413139349));
        secondFlight.setArrival(new Date(1413139352));

        assertFalse(flightDAO.isPlaneAvailableForFlight(plane.getId(), secondFlight));

        secondFlight.setDeparture(new Date(1413139350));
        secondFlight.setArrival(new Date(1413139352));

        assertFalse(flightDAO.isPlaneAvailableForFlight(plane.getId(), secondFlight));
    }

    @Test
    public void testIsPlaneAvailableIntersectInterval() {
        flightDAO.create(flight);

        Flight secondFlight = prepareSecondFlight();
        secondFlight.setDeparture(new Date(1413139349));
        secondFlight.setArrival(new Date(1413139351));

        assertFalse(flightDAO.isPlaneAvailableForFlight(plane.getId(), secondFlight));
    }

    @Test
    public void testIsPlaneAvailableOkBefore() {
        flightDAO.create(flight);

        Flight secondFlight = prepareSecondFlight();
        secondFlight.setDeparture(new Date(1413139335));
        secondFlight.setArrival(new Date(1413139339));

        assertTrue(flightDAO.isPlaneAvailableForFlight(plane.getId(), secondFlight));
    }

    @Test
    public void testIsPlaneAvailableOkAfter() {
        flightDAO.create(flight);

        Flight secondFlight = prepareSecondFlight();
        secondFlight.setDeparture(new Date(1413139351));
        secondFlight.setArrival(new Date(1413139355));

        assertTrue(flightDAO.isPlaneAvailableForFlight(plane.getId(), secondFlight));
    }

    @Test
    public void testIsPlaneAvailableExclusive() {
        flightDAO.create(flight);

        // Do not deny plane for flight which it attends
        assertTrue(flightDAO.isPlaneAvailableForFlight(plane.getId(), flight));


        Flight secondFlight = prepareSecondFlight();
        secondFlight.setDeparture(new Date(1413139345));
        secondFlight.setArrival(new Date(1413139355));

        // But deny which id does not attend if flight times overlaps
        assertFalse(flightDAO.isPlaneAvailableForFlight(plane.getId(), secondFlight));
    }
    
    /*
     * Steward is available for flight
     */

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testIsStewardAvailableNullStewardId() {
        flightDAO.isStewardAvailableForFlight(null, flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testIsStewardAvailableNullFlight() {
        flightDAO.isStewardAvailableForFlight(steward1.getId(), null);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testIsStewardAvailableNullDeparture() {
        flight.setDeparture(null);
        flightDAO.isStewardAvailableForFlight(steward1.getId(), flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testIsStewardAvailableNullArrival() {
        flight.setArrival(null);
        flightDAO.isStewardAvailableForFlight(steward1.getId(), flight);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testIsStewardAvailableSwappedDates() {
        flight.setDeparture(new Date(1413139341));
        flight.setArrival(new Date(1413139340));
        flightDAO.isStewardAvailableForFlight(steward1.getId(), flight);
    }

    @Test
    public void testIsStewardAvailableArrivalInInterval() {
        flightDAO.create(flight);

        Flight secondFlight = prepareSecondFlight();
        secondFlight.setDeparture(new Date(1413139339));
        secondFlight.setArrival(new Date(1413139340));

        assertFalse(flightDAO.isStewardAvailableForFlight(steward1.getId(), secondFlight));

        secondFlight.setDeparture(new Date(1413139339));
        secondFlight.setArrival(new Date(1413139341));
        assertFalse(flightDAO.isStewardAvailableForFlight(steward1.getId(), secondFlight));
    }

    @Test
    public void testIsStewardAvailableBothInInterval() {
        flightDAO.create(flight);

        Flight secondFlight = prepareSecondFlight();
        secondFlight.setDeparture(new Date(1413139341));
        secondFlight.setArrival(new Date(1413139342));

        assertFalse(flightDAO.isStewardAvailableForFlight(steward1.getId(), secondFlight));
    }

    @Test
    public void testIsStewardAvailableDepartureInInterval() {
        flightDAO.create(flight);

        Flight secondFlight = prepareSecondFlight();
        secondFlight.setDeparture(new Date(1413139349));
        secondFlight.setArrival(new Date(1413139352));

        assertFalse(flightDAO.isStewardAvailableForFlight(steward1.getId(), secondFlight));

        secondFlight.setDeparture(new Date(1413139350));
        secondFlight.setArrival(new Date(1413139352));

        assertFalse(flightDAO.isStewardAvailableForFlight(steward1.getId(), secondFlight));
    }

    @Test
    public void testIsStewardAvailableIntersectInterval() {
        flightDAO.create(flight);

        Flight secondFlight = prepareSecondFlight();
        secondFlight.setDeparture(new Date(1413139349));
        secondFlight.setArrival(new Date(1413139351));

        assertFalse(flightDAO.isStewardAvailableForFlight(steward1.getId(), secondFlight));
    }

    @Test
    public void testIsStewardAvailableOkBefore() {
        flightDAO.create(flight);

        Flight secondFlight = prepareSecondFlight();
        secondFlight.setDeparture(new Date(1413139335));
        secondFlight.setArrival(new Date(1413139339));

        assertTrue(flightDAO.isStewardAvailableForFlight(steward1.getId(), secondFlight));
    }

    @Test
    public void testIsStewardAvailableOkAfter() {
        flightDAO.create(flight);

        Flight secondFlight = prepareSecondFlight();
        secondFlight.setDeparture(new Date(1413139351));
        secondFlight.setArrival(new Date(1413139355));

        assertTrue(flightDAO.isStewardAvailableForFlight(steward1.getId(), secondFlight));
    }

    @Test
    public void testIsStewardAvailableExclusive() {
        flightDAO.create(flight);

        // Do not deny steward for flight which he attends to
        assertTrue(flightDAO.isStewardAvailableForFlight(steward1.getId(), flight));


        Flight secondFlight = prepareSecondFlight();
        secondFlight.setDeparture(new Date(1413139345));
        secondFlight.setArrival(new Date(1413139355));

        // But deny which he does not attend if it flights at same time
        assertFalse(flightDAO.isStewardAvailableForFlight(steward1.getId(), secondFlight));
    }
}
