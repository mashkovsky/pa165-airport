package cz.muni.fi.pa165.airport.ui;

import cz.muni.fi.pa165.airport.api.dto.DeleteResponseDTO;
import cz.muni.fi.pa165.airport.api.dto.FlightDetailDTO;
import cz.muni.fi.pa165.airport.api.dto.FlightMinimalDTO;
import cz.muni.fi.pa165.airport.api.security.CustomRole;
import cz.muni.fi.pa165.airport.api.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Matej Chrenko
 */
@RestController
@RequestMapping("/flights")
@PreAuthorize("hasRole('" + CustomRole.LOGGED + "')")
public class FlightController {

    @Autowired
    private IFlightService flightService;


    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public FlightDetailDTO create(@RequestBody FlightDetailDTO flight) {
        flightService.createFlight(flight);
        return flight;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<FlightMinimalDTO> getAllFlights() {
        return flightService.getAllFlights();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public FlightDetailDTO getFlightDetail(@PathVariable long id) {
        return flightService.getFlightDetail(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public DeleteResponseDTO delete(@PathVariable long id) {
        flightService.deleteFlight(id);
        return new DeleteResponseDTO();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public FlightDetailDTO update(@PathVariable long id, @RequestBody FlightDetailDTO flight) {
        flight.setId(id);
        flightService.updateFlight(flight);
        return flight;
    }
}
