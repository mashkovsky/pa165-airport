package cz.muni.fi.pa165.airport.service;

import cz.muni.fi.pa165.airport.dao.IDestinationDAO;
import cz.muni.fi.pa165.airport.dao.IFlightDAO;
import cz.muni.fi.pa165.airport.dao.IPlaneDAO;
import cz.muni.fi.pa165.airport.dao.IStewardDAO;
import cz.muni.fi.pa165.airport.entity.Flight;
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
        if (flight.getDeparture().after(flight.getArrival())) {
            throw new IllegalStateException("Departure is after arrival");
        }

        Flight entity = mapper.map(flight, Flight.class);

        // Check if plane is available

        // Check is stewards are available

        flightDAO.create(entity);
    }

    @Override
    public void updateFlight(FlightDetailDTO flight) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteFlight(Long flightId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<FlightMinimalDTO> getAllFlights() {
        return map(flightDAO.getAll(), FlightMinimalDTO.class);
    }

    @Override
    public FlightDetailDTO getFlightDetail(Long flightId) {

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
