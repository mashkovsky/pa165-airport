package cz.muni.fi.pa165.airport.service;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Inject dozer mapper into conversion aware service
     * @param service service to inject dozer to
     * @param <T>
     */
    protected <T extends ConversionAware> void injectDozer(T service) {
        List<String> mappingFiles = new ArrayList<String>();
        mappingFiles.add("mapping/global.xml");

        service.setMapper(new DozerBeanMapper(mappingFiles));
    }
}
