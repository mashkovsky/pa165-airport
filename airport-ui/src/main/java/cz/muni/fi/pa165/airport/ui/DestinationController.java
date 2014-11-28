package cz.muni.fi.pa165.airport.ui;

import cz.muni.fi.pa165.airport.api.dto.DestinationDTO;
import cz.muni.fi.pa165.airport.api.service.IDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Matej Chrenko
 */
@RestController
@RequestMapping("/destinations")
public class DestinationController {

    @Autowired
    private IDestinationService destinationService;

    //POST /destination
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public DestinationDTO create(@RequestBody DestinationDTO destination) {
        destinationService.createDestination(destination);
        return destination;
    }

    //GET /destination
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<DestinationDTO> index() {
        List<DestinationDTO> destinations = destinationService.getAllDestinations();
        return destinations;
    }

    //GET /destination/{id}
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DestinationDTO index(@PathVariable long id) {
        DestinationDTO destination = destinationService.getDestination(id);
        return destination;
    }

    //DELETE /destination/{id}
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        destinationService.deleteDestination(id);
    }

    //PUT /destination/{id}
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public DestinationDTO index(@PathVariable long id, @RequestBody DestinationDTO destination) {

        destination.setId(id);
        destinationService.updateDestination(destination);
        return destination;
    }

}
