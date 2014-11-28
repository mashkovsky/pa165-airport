package cz.muni.fi.pa165.airport.ui;

import cz.muni.fi.pa165.airport.api.dto.StewardDTO;
import cz.muni.fi.pa165.airport.api.service.IStewardService;
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
@RequestMapping("/stewards")
public class StewardController {

    @Autowired
    private IStewardService stewardService;

    //POST /steward
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json")
    public StewardDTO create(@RequestBody StewardDTO steward) {
        stewardService.createSteward(steward);
        return steward;
    }

    //GET /steward
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<StewardDTO> index() {
        List<StewardDTO> stewards = stewardService.getAllStewards();
        return stewards;
    }

    //GET /steward/{id}
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public StewardDTO index(@PathVariable long id) {
        StewardDTO steward = stewardService.getSteward(id);
        return steward;
    }

    //DELETE /steward/{id}
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        stewardService.deleteSteward(id);
    }

    //PUT /steward/{id}
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public StewardDTO index(@PathVariable long id, @RequestBody StewardDTO steward) {

        steward.setId(id);
        stewardService.updateSteward(steward);
        return steward;
    }

}
