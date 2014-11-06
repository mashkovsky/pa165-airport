package cz.muni.fi.pa165.airport.service;

import cz.muni.fi.pa165.airport.dao.dao.IPlaneDAO;
import cz.muni.fi.pa165.airport.dao.entity.Plane;
import cz.muni.fi.pa165.airport.service.service.PlaneService;
import cz.muni.fi.pa165.airport.service.service.dto.PlaneDTO;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;  
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

/**
 *
 * @author Jan Jilek
 */
public class PlaneServiceTest extends BaseServiceTest {
    
    @Mock private IPlaneDAO planeDAO;
    
    @InjectMocks
    private PlaneService planeService;
    
    @Before
    @Override
    public void setUp() {
        super.setUp();
        // Inject dozer mapper into service - it hasn't access to Spring context so we have to do it manually
        planeService.setMapper(mapper);
    }
    
    @Test
    public void testGetAllEmpty() {
        when(planeDAO.getAll()).thenReturn(new ArrayList<Plane>());
        assertTrue(planeService.getAllPlanes().isEmpty());
    }
    
    /*
     * Create tests
     */
    @Test
    public void testCreate() {
        // Create plane
        Plane plane = ServiceTestHelper.preparePlane(null, 10, "Plane", "007");

        // Make DTO from entity (assume dozer conversion is tested correctly)
        PlaneDTO dto = mapper.map(plane, PlaneDTO.class);

        // Create plane
        planeService.createPlane(dto);

        // Get entity value that was passed to DAO from service
        ArgumentCaptor<Plane> argument = ArgumentCaptor.forClass(Plane.class);
        verify(planeDAO).create(argument.capture());

        // Check that service sent correct entity to DAO with no attributes changed
        AssertTestHelper.assertDeepEqualPlane(argument.getValue(), dto);
    }
    
    @Test
    public void testCreateNotNullId() {
        // Create plane
        Plane plane = ServiceTestHelper.preparePlane(1L, 10, "Plane", "007");

        // Make DTO from entity (assume dozer conversion is tested correctly)
        PlaneDTO dto = mapper.map(plane, PlaneDTO.class);

        // Create plane, should fail because ID is not null
        try {
            // Create plane
            planeService.createPlane(dto);
            fail();
        } catch (IllegalArgumentException e) {
            // OK
        }

        verify(planeDAO, times(0)).create(any(Plane.class));
    }
    
    @Test
    public void testCreateZeroCapacity() {
        // Create plane
        Plane plane = ServiceTestHelper.preparePlane(null, 0, "Plane", "007");

        // Make DTO from entity (assume dozer conversion is tested correctly)
        PlaneDTO dto = mapper.map(plane, PlaneDTO.class);

        // Create plane, should fail because ID is not null
        try {
            // Create plane
            planeService.createPlane(dto);
            fail();
        } catch (IllegalArgumentException e) {
            // OK
        }

        verify(planeDAO, times(0)).create(any(Plane.class));
    }
    
    /*
     * Update tests
     */
    @Test
    public void testUpdate() {
       // Create plane
        Plane plane = ServiceTestHelper.preparePlane(1L, 10, "Plane", "007");

        // Make DTO from entity (assume dozer conversion is tested correctly)
        PlaneDTO dto = mapper.map(plane, PlaneDTO.class);

        // Create plane
        planeService.updatePlane(dto);

        // Get entity value that was passed to DAO from service
        ArgumentCaptor<Plane> argument = ArgumentCaptor.forClass(Plane.class);
        verify(planeDAO).update(argument.capture());

        // Check that service sent correct entity to DAO with no attributes changed
        AssertTestHelper.assertDeepEqualPlane(argument.getValue(), dto);
    }
    
    @Test
    public void testUpdateNullId() {
       // Create plane
        Plane plane = ServiceTestHelper.preparePlane(null, 10, "Plane", "007");

        // Make DTO from entity (assume dozer conversion is tested correctly)
        PlaneDTO dto = mapper.map(plane, PlaneDTO.class);

        // Create plane
        try {
            planeService.updatePlane(dto);
            fail();
        } catch(IllegalArgumentException e) {
            // OK
        }

        verify(planeDAO, times(0)).create(any(Plane.class));
    }
    
    @Test
    public void testUpdateZeroCapacity() {
       // Create plane
        Plane plane = ServiceTestHelper.preparePlane(1L, 0, "Plane", "007");

        // Make DTO from entity (assume dozer conversion is tested correctly)
        PlaneDTO dto = mapper.map(plane, PlaneDTO.class);

        // Create plane
        try {
            planeService.updatePlane(dto);
            fail();
        } catch(IllegalArgumentException e) {
            // OK
        }

        verify(planeDAO, times(0)).create(any(Plane.class));
    }
    
    /*
     *  Delete tests
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNullID() {
        planeService.deletePlane(null);
    }
    
    @Test
    public void testDelete() {
        planeService.deletePlane(1L);

        // Get ID value that was passed to DAO from service
        ArgumentCaptor<Long> argument = ArgumentCaptor.forClass(Long.class);
        verify(planeDAO).delete(argument.capture());

        // Check that service sent correct ID to DAO
        assertEquals(argument.getValue(), Long.valueOf(1));
    }
    
    /*
     * Test get plane
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetPlaneNullId() {
        planeService.getPlane(null);
    }
    
    @Test
    public void testGetPlane() {
        // Prepare plane
        Plane plane = ServiceTestHelper.preparePlane(1L, 0, "Plane", "007");
        
        // Configure mock DAO
        when(planeDAO.find(plane.getId())).thenReturn(plane);

        // Check that conversion between entity -> DTO works
        PlaneDTO planeDTO = planeService.getPlane(plane.getId());

        AssertTestHelper.assertDeepEqualPlane(plane, planeDTO);
    }
}
