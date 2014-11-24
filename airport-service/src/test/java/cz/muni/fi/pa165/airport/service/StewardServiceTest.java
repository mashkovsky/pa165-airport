package cz.muni.fi.pa165.airport.service;

import cz.muni.fi.pa165.airport.api.dto.StewardDTO;
import cz.muni.fi.pa165.airport.dao.dao.IStewardDAO;
import cz.muni.fi.pa165.airport.dao.entity.Steward;
import cz.muni.fi.pa165.airport.service.service.StewardService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Matej Chrenko
 */
public class StewardServiceTest extends BaseServiceTest {

    @Mock
    private IStewardDAO stewardDAO;

    @InjectMocks
    private StewardService stewardService;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        stewardService.setMapper(mapper);
    }

    @Test
    public void testGetAllEmpty() {
        when(stewardDAO.getAll()).thenReturn(new ArrayList<Steward>());
        assertTrue(stewardService.getAllStewards().isEmpty());
    }

    /*
     * Create tests
     */
    @Test
    public void testCreate() {
        Steward steward = ServiceTestHelper.prepareSteward(null, "Bob", "Dylan");
        StewardDTO dto = mapper.map(steward, StewardDTO.class);

        stewardService.createSteward(dto);
        ArgumentCaptor<Steward> argument = ArgumentCaptor.forClass(Steward.class);
        verify(stewardDAO).create(argument.capture());
        AssertTestHelper.assertDeepEqualSteward(argument.getValue(), dto);
    }

    @Test
    public void testCreateNotNullId() {
        Steward steward = ServiceTestHelper.prepareSteward(1L, "Bob", "Dylan");

        StewardDTO dto = mapper.map(steward, StewardDTO.class);

        try {
            stewardService.createSteward(dto);
            fail();
        } catch (IllegalArgumentException e) {
            // OK
        }

        verify(stewardDAO, times(0)).create(any(Steward.class));
    }

    /*
     * Update tests
     */
    @Test
    public void testUpdate() {
        Steward steward = ServiceTestHelper.prepareSteward(1L, "Bob", "Praha");
        StewardDTO dto = mapper.map(steward, StewardDTO.class);

        stewardService.updateSteward(dto);

        ArgumentCaptor<Steward> argument = ArgumentCaptor.forClass(Steward.class);
        verify(stewardDAO).update(argument.capture());

        AssertTestHelper.assertDeepEqualSteward(argument.getValue(), dto);
    }

    @Test
    public void testUpdateNullId() {
        Steward steward = ServiceTestHelper.prepareSteward(null, "Bob", "Ostrava");
        StewardDTO dto = mapper.map(steward, StewardDTO.class);

        try {
            stewardService.updateSteward(dto);
            fail();
        } catch (IllegalArgumentException e) {
            // OK
        }

        verify(stewardDAO, times(0)).create(any(Steward.class));
    }

    /*
     *  Delete tests
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNullID() {
        stewardService.deleteSteward(null);
    }

    @Test
    public void testDelete() {
        stewardService.deleteSteward(1L);

        ArgumentCaptor<Long> argument = ArgumentCaptor.forClass(Long.class);
        verify(stewardDAO).delete(argument.capture());

        assertEquals(argument.getValue(), Long.valueOf(1));
    }

    /*
     * Test get steward
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetStewardNullId() {
        stewardService.getSteward(null);
    }

    @Test
    public void testGetSteward() {
        Steward steward = ServiceTestHelper.prepareSteward(1L, "Bob", "Dylan");
        when(stewardDAO.find(steward.getId())).thenReturn(steward);
        StewardDTO detail = stewardService.getSteward(steward.getId());

        AssertTestHelper.assertDeepEqualSteward(steward, detail);
    }

}
