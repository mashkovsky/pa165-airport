package cz.muni.fi.pa165.airport.dao;

import cz.muni.fi.pa165.airport.dao.dao.IPlaneDAO;
import cz.muni.fi.pa165.airport.dao.entity.Plane;
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
public class PlaneDAOTest extends BaseDAOTest {

    @Autowired
    private IPlaneDAO dao;


    /*
     * Create
     */

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNotNullId() {
        dao.create(createPlane(1L, "Airbus", "A180", 30));
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNullName() {
        dao.create(createPlane(1L, null, "A180", 30));
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNullType() {
        dao.create(createPlane(1L, "Airbus", null, 30));
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateBadCapacity() {
        dao.create(createPlane(1L, "Airbus", null, 0));
    }

    @Test
    public void testCreate() {
        Plane plane = createPlane(null, "Airbus", "A180", 30);

        dao.create(plane);
        assertNotNull(plane.getId());

        Plane fromDb = dao.find(plane.getId());
        assertEquals(plane, fromDb);
    }

     /*
     * Uodate
     */

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdateNullId() {
        dao.update(createPlane(null, "Airbus", "A180", 30));
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdateNullName() {
        Plane plane = createPlane(null, "Airbus", "A180", 30);
        dao.create(plane);

        plane.setName(null);
        dao.update(plane);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdateNullType() {
        Plane plane = createPlane(null, "Airbus", "A180", 30);
        dao.create(plane);

        plane.setType(null);
        dao.update(plane);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testUpdateWrongCapacity() {
        Plane plane = createPlane(null, "Airbus", "A180", 30);
        dao.create(plane);

        plane.setCapacity(0);
        dao.update(plane);
    }

    @Test
    public void testUpdate() {
        Plane plane = createPlane(null, "Airbus", "A180", 30);
        dao.create(plane);

        plane.setName("Other");
        plane.setType("XYZ");
        plane.setCapacity(115);

        dao.update(plane);

        Plane fromDb = dao.find(plane.getId());
        assertEquals("Other", fromDb.getName());
        assertEquals("XYZ", fromDb.getType());
        assertEquals(new Integer(115), fromDb.getCapacity());

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
        Plane plane = createPlane(null, "Airbus", "A180", 30);
        dao.create(plane);

        Plane fromDb = dao.find(plane.getId());
        assertEquals(plane, fromDb);
        assertEquals("Airbus", fromDb.getName());
        assertEquals("A180", fromDb.getType());
        assertEquals(new Integer(30), fromDb.getCapacity());
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
        Plane plane1 = createPlane(null, "Airbus", "A180", 30);
        Plane plane2 = createPlane(null, "Airbus", "A256", 123);
        dao.create(plane1);
        dao.create(plane2);

        dao.delete(plane1.getId());

        assertNull(dao.find(plane1.getId()));
        assertEquals(plane2, dao.find(plane2.getId()));
    }

    @Test
    public void testGetAll() {
        Plane plane1 = createPlane(null, "Airbus", "A180", 30);
        Plane plane2 = createPlane(null, "Airbus", "A256", 123);
        dao.create(plane1);
        dao.create(plane2);

        List<Plane> fromDb = dao.getAll();
        assertEquals(2, fromDb.size());
        assertTrue(fromDb.contains(plane1));
        assertTrue(fromDb.contains(plane2));
    }


    private static Plane createPlane(Long id, String name, String type, int capacity) {
        Plane plane = new Plane();
        plane.setId(id);
        plane.setName(name);
        plane.setType(type);
        plane.setCapacity(capacity);

        return plane;
    }
    
}
