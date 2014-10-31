package cz.muni.fi.pa165.airport.service;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
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

    protected Mapper mapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        List<String> mappingFiles = new ArrayList<String>();
        mappingFiles.add("mapping/global.xml");
        mapper = new DozerBeanMapper(mappingFiles);
    }
}
