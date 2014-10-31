package cz.muni.fi.pa165.airport.dao;

import cz.muni.fi.pa165.airport.dao.dao.IDestinationDAO;
import cz.muni.fi.pa165.airport.dao.entity.Destination;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Zdenek Kanovsky
 */
public class DestinationDAOTest extends BaseDAOTest {

    @Autowired
    private IDestinationDAO dao;

    /*
     * Create
     */

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNotNullId() {
        dao.create(createDestination(1L, "CZ", "Brno"));
    }
    
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNullCountry() {
        dao.create(createDestination(null, null, "Brno"));
    }
    
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNullCity() {
        dao.create(createDestination(null, "CZ", null));
    }


    @Test
    public void testCreate() {
        Destination destination = createDestination(null, "CZ", "Brno");

        dao.create(destination);
        assertNotNull(destination.getId());

        Destination fromDb = dao.find(destination.getId());
        assertEquals(destination, fromDb);
    }

     /*
     * Uodate
     */

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdateNullId() {
        dao.update(createDestination(null, "CZ", "Brno"));
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdateNullCountry() {
        Destination destination = createDestination(null, "CZ", "Brno");
        dao.create(destination);

        destination.setCountry(null);
        dao.update(destination);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdateNullCity() {
        Destination destination = createDestination(null, "CZ", "Brno");
        dao.create(destination);

        destination.setCity(null);
        dao.update(destination);
    }

    @Test
    public void testUpdate() {
        Destination destination = createDestination(null, "CZ", "Brno");
        dao.create(destination);

        destination.setCountry("GE");
        destination.setCity("M�nchen");

        dao.update(destination);

        Destination fromDb = dao.find(destination.getId());
        assertEquals("GE", fromDb.getCountry());
        assertEquals("M�nchen", fromDb.getCity());
    }


    /*
     * Find
     */
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testFindNullId() {
        dao.find(null);
    }

    @Test
    public void testFindNotExistent() {
        assertNull(dao.find(1L));
    }

    @Test
    public void testFind() {
        Destination destination = createDestination(null, "CZ", "Brno");
        dao.create(destination);
        
        Destination fromDb = dao.find(destination.getId());
        assertEquals(destination, fromDb);
        assertEquals("CZ", fromDb.getCountry());
        assertEquals("Brno", fromDb.getCity());
    }

    /*
     * Delete
     */
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testDeleteNullId() {
        dao.delete(null);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testDeleteNotExistent() {
        dao.delete(1L);
    }

    @Test
    public void testDelete() {        
        Destination destination1 = createDestination(null, "CZ", "Brno");
        Destination destination2 = createDestination(null, "GE", "M�nchen");
        dao.create(destination1);
        dao.create(destination2);
        
        dao.delete(destination1.getId());
        
        assertNull(dao.find(destination1.getId()));
        assertEquals(destination2, dao.find(destination2.getId()));
    }

    @Test
    public void testGetAll() {
        Destination destination1 = createDestination(null, "CZ", "Brno");
        Destination destination2 = createDestination(null, "GE", "M�nchen");
        dao.create(destination1);
        dao.create(destination2);

        List<Destination> fromDb = dao.getAll();
        assertEquals(2, fromDb.size());
        assertTrue(fromDb.contains(destination1));
        assertTrue(fromDb.contains(destination2));
    }


    private static Destination createDestination(Long id, String country, String city) {
        Destination destination = new Destination();
        destination.setId(id);
        destination.setCountry(country);
        destination.setCity(city);

        return destination;
    }
    
}
