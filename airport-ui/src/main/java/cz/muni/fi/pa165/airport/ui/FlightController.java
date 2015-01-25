package cz.muni.fi.pa165.airport.ui;

import cz.muni.fi.pa165.airport.api.dto.DeleteResponseDTO;
import cz.muni.fi.pa165.airport.api.dto.FlightDetailDTO;
import cz.muni.fi.pa165.airport.api.dto.FlightMinimalDTO;
import cz.muni.fi.pa165.airport.api.service.IFlightService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author Matej Chrenko
 */
@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private IFlightService flightService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public FlightDetailDTO create(@RequestBody FlightDetailDTO flight) {
        flightService.createFlight(flight);
        return flight;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<FlightMinimalDTO> getAllFlights(@RequestHeader(value = "token") String token, HttpServletResponse response) throws IOException {
        if (this.validateToken(token)) {
            return flightService.getAllFlights();
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not authorized.");
        return null;
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

    private Boolean validateToken(String token) {
        String requiredToken = "123";
        return (token.equals(requiredToken));
    }
}
