package cz.muni.fi.pa165.airport.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Abstract class for all DAO tests, extend for turning on Spring context configuration and dependency injection
 *
 * @author Mariia Shevchenko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration
@ContextConfiguration(locations = {"classpath*:daoApplicationContext.xml"})
@ActiveProfiles(profiles = "testing")
@Transactional
public abstract class BaseDAOTest {
}
