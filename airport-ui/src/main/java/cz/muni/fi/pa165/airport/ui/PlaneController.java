package cz.muni.fi.pa165.airport.ui;

import cz.muni.fi.pa165.airport.api.dto.DeleteResponseDTO;
import cz.muni.fi.pa165.airport.api.dto.PlaneDTO;
import cz.muni.fi.pa165.airport.api.service.IPlaneService;
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
@RequestMapping("/planes")
public class PlaneController {

    @Autowired
    private IPlaneService planeService;

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
}
