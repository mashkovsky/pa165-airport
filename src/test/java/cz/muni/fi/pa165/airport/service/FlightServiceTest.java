package cz.muni.fi.pa165.airport.service;

import cz.muni.fi.pa165.airport.dao.IDestinationDAO;
import cz.muni.fi.pa165.airport.dao.IFlightDAO;
import cz.muni.fi.pa165.airport.dao.IPlaneDAO;
import cz.muni.fi.pa165.airport.dao.IStewardDAO;
import cz.muni.fi.pa165.airport.entity.Destination;
import cz.muni.fi.pa165.airport.entity.Flight;
import cz.muni.fi.pa165.airport.entity.Plane;
import cz.muni.fi.pa165.airport.entity.Steward;
import cz.muni.fi.pa165.airport.service.dto.FlightDetailDTO;
import cz.muni.fi.pa165.airport.service.dto.FlightMinimalDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Mariia Schevchenko
 */
public class FlightServiceTest extends BaseServiceTest {

    @Mock private IPlaneDAO planeDAO;
    @Mock private IFlightDAO flightDAO;
    @Mock private IStewardDAO stewardDAO;
    @Mock private IDestinationDAO destinationDAO;

    @InjectMocks
    private FlightService flightService;

    @Before
    public void setUp() {
        super.setUp();
        // Inject dozer mapper into service - it hasn't access to Spring context so we have to do it manually
        flightService.setMapper(mapper);
    }

    @Test
    public void testGetAllEmpty() {
        when(flightDAO.getAll()).thenReturn(new ArrayList<Flight>());
        assertTrue(flightService.getAllFlights().isEmpty());
    }

    @Test
    public void testCreate() {
        // Create flight
        Flight flight = prepareFlight();
        flight.setId(null);

        // Make DTO from entity (assume dozer conversion is tested correctly)
        FlightDetailDTO dto = mapper.map(flight, FlightDetailDTO.class);

        when(flightDAO.isPlaneAvailableForFlight(anyLong(), any(Flight.class))).thenReturn(Boolean.TRUE);
        when(flightDAO.isStewardAvailableForFlight(anyLong(), any(Flight.class))).thenReturn(Boolean.TRUE);

        // Create flight
        flightService.createFlight(dto);

        // Get entity value that was passed to DAO from service
        ArgumentCaptor<Flight> argument = ArgumentCaptor.forClass(Flight.class);
        verify(flightDAO).create(argument.capture());

        // Check that service sent correct entity to DAO with no attributes changed
        AssertTestHelper.assertDeepEqualFlight(argument.getValue(), dto);
    }

    @Test
    public void testCreatePlaneNotAvailable() {
        // Create flight
        Flight flight = prepareFlight();
        flight.setId(null);

        // Make DTO from entity (assume dozer conversion is tested correctly)
        FlightDetailDTO dto = mapper.map(flight, FlightDetailDTO.class);

        when(flightDAO.isPlaneAvailableForFlight(anyLong(), any(Flight.class))).thenReturn(Boolean.FALSE);
        when(flightDAO.isStewardAvailableForFlight(anyLong(), any(Flight.class))).thenReturn(Boolean.TRUE);

        // Create flight, should fail because no plane is available
        try {
            flightService.createFlight(dto);
            fail();
        } catch (IllegalStateException e) {
            // OK
        }

        // Check that DAO was not called at all
        verify(flightDAO, times(0)).create(any(Flight.class));
    }

    @Test
    public void testCreateStewardNotAvailable() {
        // Create flight
        Flight flight = prepareFlight();
        flight.setId(null);

        // Make DTO from entity (assume dozer conversion is tested correctly)
        FlightDetailDTO dto = mapper.map(flight, FlightDetailDTO.class);

        when(flightDAO.isPlaneAvailableForFlight(anyLong(), any(Flight.class))).thenReturn(Boolean.TRUE);
        when(flightDAO.isStewardAvailableForFlight(anyLong(), any(Flight.class))).thenReturn(Boolean.FALSE);

        // Create flight, should fail because no plane is available
        try {
            flightService.createFlight(dto);
            fail();
        } catch (IllegalStateException e) {
            // OK
        }

        // Check that DAO was not called at all
        verify(flightDAO, times(0)).create(any(Flight.class));
    }

    @Test
    public void testCreateWrongTimes() {
        // Create flight
        Flight flight = prepareFlight();
        flight.setId(null);

        // Set departure after arrival
        flight.setDeparture(new Date(1413139350));
        flight.setArrival(new Date(1413139340));

        // Make DTO from entity (assume dozer conversion is tested correctly)
        FlightDetailDTO dto = mapper.map(flight, FlightDetailDTO.class);

        // Create flight
        try {
            flightService.createFlight(dto);
            fail("It should not be possible to create flight with departure after arrival");
        } catch (IllegalStateException e) {
            // OK
        }

        // Check that DAO was not called at all
        verify(flightDAO, times(0)).create(any(Flight.class));
    }

    /*
     * Update tests
     */

    @Test
    public void testUpdate() {
        // Create flight
        Flight flight = prepareFlight();

        // Make DTO from entity (assume dozer conversion is tested correctly)
        FlightDetailDTO dto = mapper.map(flight, FlightDetailDTO.class);

        when(flightDAO.isPlaneAvailableForFlight(anyLong(), any(Flight.class))).thenReturn(Boolean.TRUE);
        when(flightDAO.isStewardAvailableForFlight(anyLong(), any(Flight.class))).thenReturn(Boolean.TRUE);

        // Update flight
        flightService.updateFlight(dto);

        // Get entity value that was passed to DAO from service
        ArgumentCaptor<Flight> argument = ArgumentCaptor.forClass(Flight.class);
        verify(flightDAO).update(argument.capture());

        // Check that service sent correct entity to DAO with no attributes changed
        AssertTestHelper.assertDeepEqualFlight(argument.getValue(), dto);
    }

    @Test
    public void testUpdateNullId() {
        // Create flight
        Flight flight = prepareFlight();
        flight.setId(null);

        // Make DTO from entity (assume dozer conversion is tested correctly)
        FlightDetailDTO dto = mapper.map(flight, FlightDetailDTO.class);

        try {
            flightService.updateFlight(dto);
            fail();
        } catch (IllegalArgumentException e) {
            // OK
        }

        // Check that DAO was not called at all
        verify(flightDAO, times(0)).update(any(Flight.class));
    }

    @Test
    public void testUpdatePlaneNotAvailable() {
        // Create flight
        Flight flight = prepareFlight();

        // Make DTO from entity (assume dozer conversion is tested correctly)
        FlightDetailDTO dto = mapper.map(flight, FlightDetailDTO.class);

        when(flightDAO.isPlaneAvailableForFlight(anyLong(), any(Flight.class))).thenReturn(Boolean.FALSE);
        when(flightDAO.isStewardAvailableForFlight(anyLong(), any(Flight.class))).thenReturn(Boolean.TRUE);

        // Update flight, should fail because no plane is available
        try {
            flightService.updateFlight(dto);
            fail();
        } catch (IllegalStateException e) {
            // OK
        }

        // Check that DAO was not called at all
        verify(flightDAO, times(0)).update(any(Flight.class));
    }

    @Test
    public void testUpdateStewardNotAvailable() {
        // Create flight
        Flight flight = prepareFlight();

        // Make DTO from entity (assume dozer conversion is tested correctly)
        FlightDetailDTO dto = mapper.map(flight, FlightDetailDTO.class);

        when(flightDAO.isPlaneAvailableForFlight(anyLong(), any(Flight.class))).thenReturn(Boolean.TRUE);
        when(flightDAO.isStewardAvailableForFlight(anyLong(), any(Flight.class))).thenReturn(Boolean.FALSE);

        // Update flight, should fail because no plane is available
        try {
            flightService.updateFlight(dto);
            fail();
        } catch (IllegalStateException e) {
            // OK
        }

        // Check that DAO was not called at all
        verify(flightDAO, times(0)).update(any(Flight.class));
    }

    @Test
    public void testUpdateWrongTimes() {
        // Create flight
        Flight flight = prepareFlight();

        // Set departure after arrival
        flight.setDeparture(new Date(1413139350));
        flight.setArrival(new Date(1413139340));

        // Make DTO from entity (assume dozer conversion is tested correctly)
        FlightDetailDTO dto = mapper.map(flight, FlightDetailDTO.class);

        // Udate flight
        try {
            flightService.updateFlight(dto);
            fail("It should not be possible to create flight with departure after arrival");
        } catch (IllegalStateException e) {
            // OK
        }

        // Check that DAO was not called at all
        verify(flightDAO, times(0)).update(any(Flight.class));
    }

    @Test
    public void testGetAll() {
        // Prepare flights
        Flight flight1 = prepareFlight();
        // Second flight is just to test if all planes are converted, don't have to have sensible values
        Flight flight2 = ServiceTestHelper.prepareFlight(7L, new Date(1413139380), new Date(1413139390), new Plane(), new Destination(), new Destination(), new ArrayList<Steward>());

        List<Flight> flights = new ArrayList<Flight>();
        flights.add(flight1);
        flights.add(flight2);

        // Configure mock DAO
        when(flightDAO.getAll()).thenReturn(flights);

        // Check that conversion between entity -> DTO works
        List<FlightMinimalDTO> allFlights = flightService.getAllFlights();
        assertEquals(2, allFlights.size());
        AssertTestHelper.assertDeepEqualFlightMinimal(flight1, allFlights.get(0));
    }

    private Flight prepareFlight() {
        Plane plane = ServiceTestHelper.preparePlane(1L, 100, "Airbus", "A180");
        Destination origin = ServiceTestHelper.prepareDestination(2L, "CZ", "Prague");
        Destination destination = ServiceTestHelper.prepareDestination(3L, "FR", "Paris");
        Steward stewardHomer = ServiceTestHelper.prepareSteward(4L, "Homer", "Simpson");
        Steward stewardRandy = ServiceTestHelper.prepareSteward(5L, "Randy", "Marsh");

        List<Steward> stewards = new ArrayList<Steward>();
        stewards.add(stewardHomer);
        stewards.add(stewardRandy);

        return ServiceTestHelper.prepareFlight(6L, new Date(1413139340), new Date(1413139350), plane, origin, destination, stewards);
    }
}
