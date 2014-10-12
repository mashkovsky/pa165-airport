package cz.muni.fi.pa165.airport.dao;

import cz.muni.fi.pa165.airport.entity.Steward;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author Matej Chrenko
 */
public class StewardDAOTest extends BaseDAOTest {

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

        stewardDAO.create(steward1);
        stewardDAO.create(steward2);
        stewardDAO.create(steward3);
        stewardDAO.create(steward4);

    }

    @Test
    private void test() {

    }

}
