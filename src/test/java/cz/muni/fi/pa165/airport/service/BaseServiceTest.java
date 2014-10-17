package cz.muni.fi.pa165.airport.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Base test case for service classes using mockito runner with annotations
 *
 * @author Mariia Schevchenko
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class BaseServiceTest {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
}
