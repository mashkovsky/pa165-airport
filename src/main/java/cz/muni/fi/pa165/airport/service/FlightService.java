package cz.muni.fi.pa165.airport.service;

import cz.muni.fi.pa165.airport.dao.IDestinationDAO;
import cz.muni.fi.pa165.airport.dao.IFlightDAO;
import cz.muni.fi.pa165.airport.dao.IPlaneDAO;
import cz.muni.fi.pa165.airport.dao.IStewardDAO;
import cz.muni.fi.pa165.airport.entity.Flight;
import cz.muni.fi.pa165.airport.entity.Steward;
import cz.muni.fi.pa165.airport.service.dto.FlightDetailDTO;
import cz.muni.fi.pa165.airport.service.dto.FlightMinimalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Methods of this class can throw subclasses of org.springframework.dao.DataAccessException if error occurs on
 * persistence layer
 *
 * @author Mariia Schevchenko
 */
@Service
public class FlightService extends ConversionAware implements IFlightService {

    @Autowired
    private IFlightDAO flightDAO;

    @Autowired
    private IPlaneDAO planeDAO;

    @Autowired
    private IDestinationDAO destinationDAO;

    @Autowired
    private IStewardDAO stewardDAO;


    @Override
    public void createFlight(FlightDetailDTO flight) {

        // Make sure that exclusive is*AvailableForFlight is not called => id has to be null
        if (flight.getId() != null) {
            throw new IllegalArgumentException("Flight is is not null");
        }

        if (flight.getDeparture().after(flight.getArrival())) {
            throw new IllegalStateException("Departure is after arrival");
        }

        Flight entity = mapper.map(flight, Flight.class);

        // Check if plane is available
        if (!flightDAO.isPlaneAvailableForFlight(flight.getPlane().getId(), entity)) {
            throw new IllegalStateException("Plane " + flight.getPlane().getId()
                    + " cannot be used for flight at dates from = "
                    + flight.getDeparture() + " to = " + flight.getArrival());
        }

        // Check if stewards are available
        List<Steward> stewards = map(flight.getStewards(), Steward.class);
        for (Steward steward : stewards) {
            if (!flightDAO.isStewardAvailableForFlight(steward.getId(), entity)) {
                throw new IllegalStateException("Steward " + flight.getPlane().getId()
                        + " cannot be used for flight at dates from = "
                        + flight.getDeparture() + " to = " + flight.getArrival());
            }
        }

        flightDAO.create(entity);
    }

    @Override
    public void updateFlight(FlightDetailDTO flight) {
        if (flight.getDeparture().after(flight.getArrival())) {
            throw new IllegalStateException("Departure is after arrival");
        }

        // ID is needed for exclusivity check in is*AvailableForFlight methods
        if (flight.getId() == null) {
            throw new IllegalArgumentException("Flight ID is null");
        }

        Flight entity = mapper.map(flight, Flight.class);

        // Check if plane is available
        if (!flightDAO.isPlaneAvailableForFlight(flight.getPlane().getId(), entity)) {
            throw new IllegalStateException("Plane " + flight.getPlane().getId()
                    + " cannot be used for flight at dates from = "
                    + flight.getDeparture() + " to = " + flight.getArrival());
        }

        // Check if stewards are available
        List<Steward> stewards = map(flight.getStewards(), Steward.class);
        for (Steward steward : stewards) {
            if (!flightDAO.isStewardAvailableForFlight(steward.getId(), entity)) {
                throw new IllegalStateException("Steward " + flight.getPlane().getId()
                        + " cannot be used for flight at dates from = "
                        + flight.getDeparture() + " to = " + flight.getArrival());
            }
        }

        flightDAO.update(entity);
    }

    @Override
    public void deleteFlight(Long flightId) {
        if (flightId == null) {
            throw new IllegalArgumentException("Flight ID is null");
        }

        flightDAO.delete(flightId);
    }

    @Override
    public List<FlightMinimalDTO> getAllFlights() {
        return map(flightDAO.getAll(), FlightMinimalDTO.class);
    }

    @Override
    public FlightDetailDTO getFlightDetail(Long flightId) {

        if (flightId == null) {
            throw new IllegalArgumentException("Flight ID is null");
        }

        Flight flight = flightDAO.find(flightId);

        return flight == null ? null : mapper.map(flight, FlightDetailDTO.class);
    }
}
