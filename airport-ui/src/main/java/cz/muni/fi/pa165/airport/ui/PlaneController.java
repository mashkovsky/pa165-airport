package cz.muni.fi.pa165.airport.ui;

import cz.muni.fi.pa165.airport.api.dto.PlaneDTO;
import cz.muni.fi.pa165.airport.api.service.IPlaneService;
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
@RequestMapping("/planes")
public class PlaneController {

    @Autowired
    private IPlaneService planeService;

    //POST /plane
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public PlaneDTO create(@RequestBody PlaneDTO plane) {
        planeService.createPlane(plane);
        return plane;
    }

    //GET /plane
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<PlaneDTO> index() {
        List<PlaneDTO> planes = planeService.getAllPlanes();
        return planes;
    }

    //GET /plane/{id}
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PlaneDTO index(@PathVariable long id) {
        PlaneDTO plane = planeService.getPlane(id);
        return plane;
    }

    //DELETE /plane/{id}
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        planeService.deletePlane(id);
    }

    //PUT /plane/{id}
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public PlaneDTO index(@PathVariable long id, @RequestBody PlaneDTO plane) {

        plane.setId(id);
        planeService.updatePlane(plane);
        return plane;
    }

}
//        steward.setErrorCodes(new ArrayList<String>() {{
//            add("STEWARD_NOT_AVAILABLE");
//            add("SOMETHING_ELSE");
//        }});
