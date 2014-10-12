package cz.muni.fi.pa165.airport.dao;

import cz.muni.fi.pa165.airport.entity.Steward;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Matej Chrenko
 */
public class StewardDAOTest extends BaseDAOTest {

    @Autowired
    private IStewardDAO dao;

    /*
     * Create
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNotNullId() {
        dao.create(createSteward(1L, "Janko", "Mrkvi?ka"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNullCountry() {
        dao.create(createSteward(null, null, "Mrkvi?ka"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNullCity() {
        dao.create(createSteward(null, "Janko", null));
    }

    @Test
    public void testCreate() {
        Steward steward = createSteward(null, "Janko", "Mrkvi?ka");

        dao.create(steward);
        assertNotNull(steward.getId());

        Steward fromDb = dao.find(steward.getId());
        assertEquals(steward, fromDb);
    }

    /*
     * Update
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullId() {
        dao.update(createSteward(null, "Janko", "Mrkvi?ka"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullCountry() {
        Steward steward = createSteward(null, "Janko", "Mrkvi?ka");
        dao.create(steward);

        steward.setFirstName(null);
        dao.update(steward);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullCity() {
        Steward steward = createSteward(null, "Janko", "Mrkvi?ka");
        dao.create(steward);

        steward.setLastName(null);
        dao.update(steward);
    }

    @Test
    public void testUpdate() {
        Steward steward = createSteward(null, "Janko", "Mrkvi?ka");
        dao.create(steward);

        steward.setFirstName("Homer");
        steward.setLastName("Simpson");

        dao.update(steward);

        Steward fromDb = dao.find(steward.getId());
        assertEquals("Homer", fromDb.getFirstName());
        assertEquals("Simpson", fromDb.getLastName());
    }


    /*
     * Find
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFindNullId() {
        dao.find(null);
    }

    @Test
    public void testFindNotExistent() {
        assertNull(dao.find(1L));
    }

    @Test
    public void testFind() {
        Steward steward = createSteward(null, "Janko", "Homer");
        dao.create(steward);

        Steward fromDb = dao.find(steward.getId());
        assertEquals(steward, fromDb);
        assertEquals("Janko", fromDb.getFirstName());
        assertEquals("Homer", fromDb.getLastName());
    }

    /*
     * Delete
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNullId() {
        dao.delete(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNotExistent() {
        dao.delete(1L);
    }

    @Test
    public void testDelete() {
        Steward steward1 = createSteward(null, "Janko", "Mrkvi?ka");
        Steward steward2 = createSteward(null, "Homer", "Simpson");
        dao.create(steward1);
        dao.create(steward2);

        dao.delete(steward1.getId());

        assertNull(dao.find(steward1.getId()));
        assertEquals(steward2, dao.find(steward2.getId()));
    }

    @Test
    public void testGetAll() {
        Steward steward1 = createSteward(null, "Janko", "Mrkvi?ka");
        Steward steward2 = createSteward(null, "Homer", "Simpson");
        dao.create(steward1);
        dao.create(steward2);

        List<Steward> fromDb = dao.getAll();
        assertEquals(2, fromDb.size());
        assertTrue(fromDb.contains(steward1));
        assertTrue(fromDb.contains(steward2));
    }

    private static Steward createSteward(Long id, String firstName, String lastName) {
        Steward steward = new Steward();
        steward.setId(id);
        steward.setLastName(lastName);
        steward.setFirstName(firstName);

        return steward;
    }

}
