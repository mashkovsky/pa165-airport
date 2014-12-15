package cz.muni.fi.pa165.airport.ui;

import cz.muni.fi.pa165.airport.api.dto.DeleteResponseDTO;
import cz.muni.fi.pa165.airport.api.dto.FlightDetailDTO;
import cz.muni.fi.pa165.airport.api.dto.FlightMinimalDTO;
import cz.muni.fi.pa165.airport.api.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class FlightController {

    @Autowired
    private IFlightService flightService;

    //POST /flight
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public FlightDetailDTO create(@RequestBody FlightDetailDTO flight) {
        flightService.createFlight(flight);
        return flight;
    }

    //GET /flight
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<FlightMinimalDTO> index() {
        List<FlightMinimalDTO> flights = flightService.getAllFlights();
        return flights;
    }

    //GET /flight/{id}
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public FlightDetailDTO index(@PathVariable long id) {
        FlightDetailDTO flight = flightService.getFlightDetail(id);
        return flight;
    }

    //DELETE /flight/{id}
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public DeleteResponseDTO delete(@PathVariable long id) {
        flightService.deleteFlight(id);

        return new DeleteResponseDTO(); // TODO fill this
    }

    //PUT /flight/{id}
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public FlightDetailDTO index(@PathVariable long id, @RequestBody FlightDetailDTO flight) {

        flight.setId(id);
        flightService.updateFlight(flight);
        return flight;
    }

}
//        steward.setErrorCodes(new ArrayList<String>() {{
//            add("STEWARD_NOT_AVAILABLE");
//            add("SOMETHING_ELSE");
//        }});
