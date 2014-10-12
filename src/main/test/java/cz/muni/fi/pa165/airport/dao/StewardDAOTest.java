package cz.muni.fi.pa165.airport.dao;

import cz.muni.fi.pa165.airport.entity.Steward;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Matej Chrenko
 */
public class StewardDAOTest extends BaseDAOTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private IStewardDAO stewardDAO;

    private Steward steward1;
    private Steward steward2;
    private Steward steward3;
    private Steward steward4;

    @Before
    public void setUp() {
        steward1 = new Steward();
        steward1.setFirstName("John");
        steward1.setLastName("Lennon");

        steward2 = new Steward();
        steward2.setFirstName("Benjamin");
        steward2.setLastName("Franklin");

        steward3 = new Steward();
        steward3.setFirstName("Benjamin");
        steward3.setLastName("Franklin");

        steward4 = new Steward();
        steward4.setFirstName("Ján");
        steward4.setLastName("Mrkvi?ka");

    }

    /**
     * Test of create method, of class StewardDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("Test create steward.");
        try {
            stewardDAO.create(null);
            Assert.fail("Didn't throw exception when steward is null.");
        } catch (IllegalArgumentException e) {
            //this should happen
        } catch (Exception e) {
            Assert.fail("Thrown unexpected exception when steward is null.");
        }
        try {
            steward1.setId(3L);
            stewardDAO.create(steward1);
            Assert.fail("Didn't throw exception when steward id is already set.");
        } catch (IllegalArgumentException e) {
            //this should happen
        } catch (Exception e) {
            Assert.fail("Thrown unexpected exception when steward id is already set.");
        }
        stewardDAO.create(steward2);
        Assert.assertEquals(steward2.getFirstName(), "Benjamin");
    }

}
