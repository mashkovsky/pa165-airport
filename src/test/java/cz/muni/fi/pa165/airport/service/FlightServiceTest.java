package cz.muni.fi.pa165.airport.service;

import cz.muni.fi.pa165.airport.dao.IDestinationDAO;
import cz.muni.fi.pa165.airport.dao.IFlightDAO;
import cz.muni.fi.pa165.airport.dao.IPlaneDAO;
import cz.muni.fi.pa165.airport.dao.IStewardDAO;
import cz.muni.fi.pa165.airport.entity.Destination;
import cz.muni.fi.pa165.airport.entity.Flight;
import cz.muni.fi.pa165.airport.entity.Plane;
import cz.muni.fi.pa165.airport.entity.Steward;
import cz.muni.fi.pa165.airport.service.dto.FlightMinimalDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
        // Inject dozer mapper into service - it hasn't access to Spring context so we have to do it manually
        injectDozer(flightService);
    }

    @Test
    public void testGetAllEmpty() {
        when(flightDAO.getAll()).thenReturn(new ArrayList<Flight>());
        assertTrue(flightService.getAllFlights().isEmpty());
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
