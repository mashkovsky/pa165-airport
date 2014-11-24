package cz.muni.fi.pa165.airport.service;

import cz.muni.fi.pa165.airport.api.dto.DestinationDTO;
import cz.muni.fi.pa165.airport.api.dto.FlightDetailDTO;
import cz.muni.fi.pa165.airport.api.dto.FlightMinimalDTO;
import cz.muni.fi.pa165.airport.api.dto.PlaneDTO;
import cz.muni.fi.pa165.airport.api.dto.StewardDTO;
import cz.muni.fi.pa165.airport.dao.entity.Destination;
import cz.muni.fi.pa165.airport.dao.entity.Flight;
import cz.muni.fi.pa165.airport.dao.entity.Plane;
import cz.muni.fi.pa165.airport.dao.entity.Steward;

import static org.junit.Assert.assertEquals;

/**
 * Class contains static methods for quick and simple assert if all attributes of entity equal to those from DTO
 * Usage in service mock tests
 *
 * @author Mariia Schevchenko
 */
public final class AssertTestHelper {

    private AssertTestHelper() {
        // According to Bloch, Effective Java
        throw new IllegalStateException("Do not initialize helper class");
    }


    /**
     * Assert each attribute of entity to equal each attribute of DTO
     * @param entity entity
     * @param dto dto
     */
    public static void assertDeepEqualFlightMinimal(Flight entity, FlightMinimalDTO dto) {
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getArrival(), dto.getArrival());
        assertEquals(entity.getDeparture(), dto.getDeparture());
        assertDeepEqualDestination(entity.getDestination(), dto.getDestination());
        assertDeepEqualDestination(entity.getOrigin(), dto.getOrigin());
    }

    /**
     * Assert each attribute of entity to equal each attribute of DTO
     * @param entity entity
     * @param dto dto
     */
    public static void assertDeepEqualFlight(Flight entity, FlightDetailDTO dto) {
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getArrival(), dto.getArrival());
        assertEquals(entity.getDeparture(), dto.getDeparture());
        assertDeepEqualDestination(entity.getDestination(), dto.getDestination());
        assertDeepEqualDestination(entity.getOrigin(), dto.getOrigin());
        assertDeepEqualPlane(entity.getPlane(), dto.getPlane());

        // Deeply assert all stewards. Order should not be changed between mappings so we can count on it.
        assertEquals(entity.getStewards().size(), dto.getStewards().size());
        for (int i = 0; i < entity.getStewards().size(); i++) {
            assertDeepEqualSteward(entity.getStewards().get(i), dto.getStewards().get(i));
        }
    }

    /**
     * Assert each attribute of entity to equal each attribute of DTO
     * @param entity entity
     * @param dto dto
     */
    public static void assertDeepEqualDestination(Destination entity, DestinationDTO dto) {
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getCity(), dto.getCity());
        assertEquals(entity.getCountry(), dto.getCountry());
    }

    /**
     * Assert each attribute of entity to equal each attribute of DTO
     * @param entity entity
     * @param dto dto
     */
    public static void assertDeepEqualPlane(Plane entity, PlaneDTO dto) {
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getType(), dto.getType());
        assertEquals((int)entity.getCapacity(), dto.getCapacity());
    }

    /**
     * Assert each attribute of entity to equal each attribute of DTO
     * @param entity entity
     * @param dto dto
     */
    public static void assertDeepEqualSteward(Steward entity, StewardDTO dto) {
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getFirstName(), dto.getFirstName());
        assertEquals(entity.getLastName(), dto.getLastName());
    }
}
