package cz.muni.fi.pa165.airport.ui;

import cz.muni.fi.pa165.airport.api.dto.DeleteResponseDTO;
import cz.muni.fi.pa165.airport.api.dto.PlaneDTO;
import cz.muni.fi.pa165.airport.api.service.IFlightService;
import cz.muni.fi.pa165.airport.api.service.IPlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author Matej Chrenko
 */
@RestController
@RequestMapping("/planes")
public class PlaneController {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd-hh:mm:ss";

    @Autowired
    private IPlaneService planeService;

    @Autowired
    private IFlightService flightService;


    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public PlaneDTO create(@RequestBody PlaneDTO plane) {
        planeService.createPlane(plane);
        return plane;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<PlaneDTO> getAllPlanes() {
        return planeService.getAllPlanes();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PlaneDTO getPlane(@PathVariable long id) {
        return planeService.getPlane(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public DeleteResponseDTO delete(@PathVariable long id) {
        planeService.deletePlane(id);
        return new DeleteResponseDTO();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public PlaneDTO update(@PathVariable long id, @RequestBody PlaneDTO plane) {
        plane.setId(id);
        planeService.updatePlane(plane);
        return plane;
    }

    @RequestMapping(value = "/{planeId}/{from}/{to}", method = RequestMethod.GET)
    public boolean isPlaneAvailableForFlightExclusive(
            @PathVariable Long planeId,
            @PathVariable @DateTimeFormat(pattern= DATE_TIME_FORMAT) Date from,
            @PathVariable @DateTimeFormat(pattern= DATE_TIME_FORMAT) Date to) {
        return flightService.isPlaneAvailableForFlight(planeId, from, to, null);
    }

    @RequestMapping(value = "/{planeId}/{from}/{to}/{flightId}", method = RequestMethod.GET)
    public boolean isPlaneAvailableForFlightInclusive(
            @PathVariable Long planeId,
            @PathVariable @DateTimeFormat(pattern= DATE_TIME_FORMAT) Date from,
            @PathVariable @DateTimeFormat(pattern= DATE_TIME_FORMAT) Date to,
            @PathVariable Long flightId) {
        return flightService.isPlaneAvailableForFlight(planeId, from, to, flightId);
    }
}
