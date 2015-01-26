package cz.muni.fi.pa165.airport.dao;

import cz.muni.fi.pa165.airport.dao.dao.IUserDAO;
import cz.muni.fi.pa165.airport.dao.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Mariia Schevchenko
 */
public class UserDAOTest extends BaseDAOTest {

    @Autowired
    private IUserDAO dao;

    /*
     * Create
     */
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNotNullId() {
        dao.create(createUser(1L, "Mashka", "maska@java.com", "XxXxXxXx"));
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNullName() {
        dao.create(createUser(null, null, "maska@java.com", "XxXxXxXx"));
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNullEmail() {
        dao.create(createUser(null, "Mashka", null, "XxXxXxXx"));
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testCreateNullPassword() {
        dao.create(createUser(null, "Mashka", "maska@java.com", null));
    }

    @Test
    public void testCreate() {
        User user = createUser(null, "Mashka", "maska@java.com", "XxXxXxXx");

        dao.create(user);
        assertNotNull(user.getId());

        User fromDb = dao.getUserByEmail(user.getEmail());
        assertEquals(user, fromDb);
    }

    private static User createUser(Long id, String name, String email, String password) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPrivileged(false);

        return user;
    }

}
