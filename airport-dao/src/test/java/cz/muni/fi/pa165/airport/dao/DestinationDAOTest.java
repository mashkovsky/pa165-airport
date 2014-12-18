package cz.muni.fi.pa165.airport.dao;

import cz.muni.fi.pa165.airport.dao.dao.IDestinationDAO;
import cz.muni.fi.pa165.airport.dao.dao.IFlightDAO;
import cz.muni.fi.pa165.airport.dao.dao.IPlaneDAO;
import cz.muni.fi.pa165.airport.dao.dao.IStewardDAO;
import cz.muni.fi.pa165.airport.dao.entity.Destination;
import cz.muni.fi.pa165.airport.dao.entity.Flight;
import cz.muni.fi.pa165.airport.dao.entity.Plane;
import cz.muni.fi.pa165.airport.dao.entity.Steward;
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
 * @author Zdenek Kanovsky
 */
public class DestinationDAOTest extends BaseDAOTest {

    @Autowired
    private IDestinationDAO destinationDAO;
    
    @Autowired
    private IFlightDAO flightDAO;

    @Autowired
    private IPlaneDAO planeDAO;

    @Autowired
    private IStewardDAO stewardDAO;
    
    /*
     * Create
     */
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNotNullId() {
        destinationDAO.create(createDestination(1L, "CZ", "Brno"));
    }
    
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNullCountry() {
        destinationDAO.create(createDestination(null, null, "Brno"));
    }
    
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNullCity() {
        destinationDAO.create(createDestination(null, "CZ", null));
    }

    @Test
    public void testCreate() {
        Destination destination = createDestination(null, "CZ", "Brno");

        destinationDAO.create(destination);
        assertNotNull(destination.getId());

        Destination fromDb = destinationDAO.find(destination.getId());
        assertEquals(destination, fromDb);
    }

     /*
     * Update
     */
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdateNullId() {
        destinationDAO.update(createDestination(null, "CZ", "Brno"));
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdateNullCountry() {
        Destination destination = createDestination(null, "CZ", "Brno");
        destinationDAO.create(destination);

        destination.setCountry(null);
        destinationDAO.update(destination);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdateNullCity() {
        Destination destination = createDestination(null, "CZ", "Brno");
        destinationDAO.create(destination);

        destination.setCity(null);
        destinationDAO.update(destination);
    }

    @Test
    public void testUpdate() {
        Destination destination = createDestination(null, "CZ", "Brno");
        destinationDAO.create(destination);

        destination.setCountry("GE");
        destination.setCity("M�nchen");

        destinationDAO.update(destination);

        Destination fromDb = destinationDAO.find(destination.getId());
        assertEquals("GE", fromDb.getCountry());
        assertEquals("M�nchen", fromDb.getCity());
    }

    /*
     * Find
     */
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testFindNullId() {
        destinationDAO.find(null);
    }

    @Test
    public void testFindNotExistent() {
        assertNull(destinationDAO.find(1L));
    }

    @Test
    public void testFind() {
        Destination destination = createDestination(null, "CZ", "Brno");
        destinationDAO.create(destination);
        
        Destination fromDb = destinationDAO.find(destination.getId());
        assertEquals(destination, fromDb);
        assertEquals("CZ", fromDb.getCountry());
        assertEquals("Brno", fromDb.getCity());
    }

    /*
     * Delete
     */
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testDeleteNullId() {
        destinationDAO.delete(null);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testDeleteNotExistent() {
        destinationDAO.delete(1L);
    }

    @Test
    public void testDelete() {        
        Destination destination1 = createDestination(null, "CZ", "Brno");
        Destination destination2 = createDestination(null, "GE", "M�nchen");
        destinationDAO.create(destination1);
        destinationDAO.create(destination2);
        
        destinationDAO.delete(destination1.getId());
        
        assertNull(destinationDAO.find(destination1.getId()));
        assertEquals(destination2, destinationDAO.find(destination2.getId()));
    }

    /*
     * Other
     */
    @Test
    public void testGetAll() {
        Destination destination1 = createDestination(null, "CZ", "Brno");
        Destination destination2 = createDestination(null, "GE", "Munchen");
        Destination destination3 = createDestination(null, "GE", "Prague");
        destinationDAO.create(destination1);
        destinationDAO.create(destination2);
        destinationDAO.create(destination3);

        destinationDAO.delete(destination3.getId());

        List<Destination> fromDb = destinationDAO.getAll();
        assertEquals(2, fromDb.size());
        assertTrue(fromDb.contains(destination1));
        assertTrue(fromDb.contains(destination2));
    }
    
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testOriginNotUsedInFlightsNullId() {
        destinationDAO.isNotUsedInFlights(null);
    }
    
    @Test
    public void testOriginNotUsedInEmptyFlights() {
        Destination destination1 = createDestination(null, "CZ", "Brno");
        
        destinationDAO.create(destination1);
        
        assertTrue(destinationDAO.isNotUsedInFlights(destination1.getId()));
    }
    
    @Test
    public void testOriginNotUsedInFlights() {
        Destination destination1 = createDestination(null, "CZ", "Brno");
        Destination destination2 = createDestination(null, "GE", "Munchen");
        Destination destination3 = createDestination(null, "GB", "London");
        Destination destination4 = createDestination(null, "USA", "Washington");
        Plane plane1 = createDefaultPlane(null);
        Plane plane2 = createDefaultPlane(null);
        Steward steward1 = createDefaultSteward(null);
        Steward steward2 = createDefaultSteward(null);
        List<Steward> stewards1 = new ArrayList<Steward>();
        stewards1.add(steward1);
        List<Steward> stewards2 = new ArrayList<Steward>();
        stewards2.add(steward2);
        Flight flight1 = createFlight(destination1, destination2, plane1, stewards1);
        Flight flight2 = createFlight(destination2, destination3, plane2, stewards2);
        
        
        destinationDAO.create(destination1);
        destinationDAO.create(destination2);
        destinationDAO.create(destination3);
        destinationDAO.create(destination4);
        planeDAO.create(plane1);
        planeDAO.create(plane2);
        stewardDAO.create(steward1);
        stewardDAO.create(steward2);
        flightDAO.create(flight1);
        flightDAO.create(flight2);
        
        assertFalse(destinationDAO.isNotUsedInFlights(destination1.getId()));
        assertFalse(destinationDAO.isNotUsedInFlights(destination2.getId()));
        assertFalse(destinationDAO.isNotUsedInFlights(destination3.getId()));
        assertTrue(destinationDAO.isNotUsedInFlights(destination4.getId()));
    }

    /*
     * Private
     */
    private static Destination createDestination(Long id, String country, String city) {
        Destination destination = new Destination();
        destination.setId(id);
        destination.setCountry(country);
        destination.setCity(city);

        return destination;
    }
    
    private static Plane createDefaultPlane(Long id) {
        Plane plane = new Plane();
        plane.setName("name");
        plane.setType("type");
        plane.setCapacity(300);
        
        return plane;
    }
    
    private static Steward createDefaultSteward(Long id) {
        Steward steward = new Steward();
        steward.setFirstName("firstName");
        steward.setLastName("lastName");
        
        return steward;
    }
    
    private static Flight createFlight(Destination origin, Destination destination, Plane plane, List<Steward> stewards) {
        Flight flight = new Flight();
        flight.setDeparture(new Date(1413139340));
        flight.setArrival(new Date(1413139350));
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setPlane(plane);
        flight.setStewards(stewards);
    
        return flight;
    }
    
}
