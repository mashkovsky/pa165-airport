package cz.muni.fi.pa165.airport.service;

import cz.muni.fi.pa165.airport.api.dto.DestinationDTO;
import cz.muni.fi.pa165.airport.dao.dao.IDestinationDAO;
import cz.muni.fi.pa165.airport.dao.entity.Destination;
import cz.muni.fi.pa165.airport.service.service.DestinationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Zdenek Kanovsky
 */
public class DestinationServiceTest extends BaseServiceTest {
    
    @Mock private IDestinationDAO destinationDAO;
    
    @InjectMocks
    private DestinationService destinationService;
    
    @Before
    public void setUp() {
        super.setUp();
        // Inject dozer mapper into service - it hasn't access to Spring context so we have to do it manually
        destinationService.setMapper(mapper);
    }
    
    @Test
    public void testGetAllEmpty() {
        when(destinationDAO.getAll()).thenReturn(new ArrayList<Destination>());
        assertTrue(destinationService.getAllDestinations().isEmpty());
    }
 
    /*
     * Create tests
     */
    @Test
    public void testCreate() {
        // Create destination
        Destination destination = ServiceTestHelper.prepareDestination(null, "CZ", "Brno");

        // Make DTO from entity (assume dozer conversion is tested correctly)
        DestinationDTO dto = mapper.map(destination, DestinationDTO.class);

        // Create destination
        destinationService.createDestination(dto);

        // Get entity value that was passed to DAO from service
        ArgumentCaptor<Destination> argument = ArgumentCaptor.forClass(Destination.class);
        verify(destinationDAO).create(argument.capture());

        // Check that service sent correct entity to DAO with no attributes changed
        AssertTestHelper.assertDeepEqualDestination(argument.getValue(), dto);
    }
    
    @Test
    public void testCreateNotNullId() {
        // Create destination
        Destination destination = ServiceTestHelper.prepareDestination(1L, "CZ", "Brno");

        // Make DTO from entity (assume dozer conversion is tested correctly)
        DestinationDTO dto = mapper.map(destination, DestinationDTO.class);

        // Create destination, should fail because ID is not null
        try {
            destinationService.createDestination(dto);
            fail();
        } catch (IllegalArgumentException e) {
            // OK
        }

        // Should have been called
        verify(destinationDAO, times(0)).create(any(Destination.class));
    }
    
    /*
     * Update tests
     */
    @Test
    public void testUpdate() {
        // Create destination
        Destination destination = ServiceTestHelper.prepareDestination(1L, "CZ", "Praha");

        // Make DTO from entity (assume dozer conversion is tested correctly)
        DestinationDTO dto = mapper.map(destination, DestinationDTO.class);

        // Update destination
        destinationService.updateDestination(dto);

        // Get entity value that was passed to DAO from service
        ArgumentCaptor<Destination> argument = ArgumentCaptor.forClass(Destination.class);
        verify(destinationDAO).update(argument.capture());

        // Check that service sent correct entity to DAO with no attributes changed
        AssertTestHelper.assertDeepEqualDestination(argument.getValue(), dto);
    }
    
    @Test
    public void testUpdateNullId() {
        // Create destination
        Destination destination = ServiceTestHelper.prepareDestination(null, "CZ", "Ostrava");

        // Make DTO from entity (assume dozer conversion is tested correctly)
        DestinationDTO dto = mapper.map(destination, DestinationDTO.class);

        // Create destination, should fail because ID is not null
        try {
            destinationService.updateDestination(dto);
            fail();
        } catch (IllegalArgumentException e) {
            // OK
        }

        // Should have been called
        verify(destinationDAO, times(0)).create(any(Destination.class));
    }
    
    /*
     *  Delete tests
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNullID() {
        destinationService.deleteDestination(null);
    }
    
    @Test
    public void testDelete() {
        
        destinationService.deleteDestination(1L);

        // Get ID value that was passed to DAO from service
        ArgumentCaptor<Long> argument = ArgumentCaptor.forClass(Long.class);
        verify(destinationDAO).delete(argument.capture());

        // Check that service sent correct ID to DAO
        assertEquals(argument.getValue(), Long.valueOf(1));
    }
    
    /*
     * Test get destination
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetDestinationNullId() {
        destinationService.getDestination(null);
    }
    
    @Test
    public void testGetFlightDetailNonExistent() {
        // Configure mock DAO
        when(destinationDAO.find(anyLong())).thenReturn(null);
        assertNull(destinationService.getDestination(1L));
    }
    
    @Test
    public void testGetDestination() {
        // Prepare flights
        Destination destination = ServiceTestHelper.prepareDestination(1L, "CZ", "Brno");
        
        // Configure mock DAO
        when(destinationDAO.find(destination.getId())).thenReturn(destination);

        // Check that conversion between entity -> DTO works
        DestinationDTO detail = destinationService.getDestination(destination.getId());

        AssertTestHelper.assertDeepEqualDestination(destination, detail);
    }
    
}
