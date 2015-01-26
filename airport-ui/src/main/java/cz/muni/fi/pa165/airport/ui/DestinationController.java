package cz.muni.fi.pa165.airport.ui;

import cz.muni.fi.pa165.airport.api.dto.DeleteResponseDTO;
import cz.muni.fi.pa165.airport.api.dto.DestinationDTO;
import cz.muni.fi.pa165.airport.api.security.CustomRole;
import cz.muni.fi.pa165.airport.api.service.IDestinationService;
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
@RequestMapping("/destinations")
@PreAuthorize("hasRole('" + CustomRole.LOGGED + "')")
public class DestinationController {

    @Autowired
    private IDestinationService destinationService;


    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public DestinationDTO create(@RequestBody DestinationDTO destination) {
        destinationService.createDestination(destination);
        return destination;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<DestinationDTO> getAllDestinations() {
        return destinationService.getAllDestinations();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DestinationDTO getDestination(@PathVariable long id) {
        return destinationService.getDestination(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public DeleteResponseDTO delete(@PathVariable long id) {
        destinationService.deleteDestination(id);
        return new DeleteResponseDTO();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public DestinationDTO update(@PathVariable long id, @RequestBody DestinationDTO destination) {
        destination.setId(id);
        destinationService.updateDestination(destination);
        return destination;
    }
}
