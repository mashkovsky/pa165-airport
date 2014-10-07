package cz.muni.fi.pa165.airport.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Abstract class for all DAO tests, extend for turning on Spring context configuration and dependency injection
 *
 * @author Mariia Shevchenko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public abstract class BaseDAOTest {
}
