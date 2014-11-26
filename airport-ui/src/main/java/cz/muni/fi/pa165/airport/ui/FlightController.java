package cz.muni.fi.pa165.airport.ui;

import cz.muni.fi.pa165.airport.api.dto.StewardDTO;
import cz.muni.fi.pa165.airport.api.service.IStewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariia Schevchenko
 */
@RestController
@RequestMapping("/")
public class FlightController {

    @Autowired
    private IStewardService stewardService;


    @RequestMapping("/")
    public StewardDTO index() {

        StewardDTO steward = null;

        List<StewardDTO> stewards = stewardService.getAllStewards();
        if (stewards.isEmpty()) {
            steward = new StewardDTO();
            steward.setFirstName("Homer");
            steward.setLastName("Simpson");

            stewardService.createSteward(steward);
        } else {
            steward = stewards.get(0);
        }


        // Test that it is found
        return steward;
    }

    @RequestMapping("/error")
    public StewardDTO error() {

        StewardDTO steward = new StewardDTO();
        steward.setErrorCodes(new ArrayList<String>() {{
            add("STEWARD_NOT_AVAILABLE");
            add("SOMETHING_ELSE");
        }});

        return steward;
    }

    @RequestMapping("/{stewardName}")
    public StewardDTO index(@PathVariable String stewardName) {

        StewardDTO steward = new StewardDTO();
        steward.setFirstName(stewardName);

        return steward;
    }

}
