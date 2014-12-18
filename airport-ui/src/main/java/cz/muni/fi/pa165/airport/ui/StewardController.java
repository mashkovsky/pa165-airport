package cz.muni.fi.pa165.airport.ui;

import cz.muni.fi.pa165.airport.api.dto.DeleteResponseDTO;
import cz.muni.fi.pa165.airport.api.dto.StewardDTO;
import cz.muni.fi.pa165.airport.api.service.IFlightService;
import cz.muni.fi.pa165.airport.api.service.IStewardService;
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
@RequestMapping("/stewards")
public class StewardController {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd-hh:mm:ss";

    @Autowired
    private IStewardService stewardService;

    @Autowired
    private IFlightService flightService;


    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public StewardDTO create(@RequestBody StewardDTO steward) {
        stewardService.createSteward(steward);
        return steward;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<StewardDTO> getAllStewards() {
        return stewardService.getAllStewards();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public StewardDTO getSteward(@PathVariable long id) {
        return stewardService.getSteward(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public DeleteResponseDTO delete(@PathVariable long id) {
        stewardService.deleteSteward(id);

        return new DeleteResponseDTO();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public StewardDTO update(@PathVariable long id, @RequestBody StewardDTO steward) {
        steward.setId(id);
        stewardService.updateSteward(steward);
        return steward;
    }

    @RequestMapping(value = "/{stewardId}/{from}/{to}", method = RequestMethod.GET)
    public boolean isStewardAvailableForFlightExclusive(
            @PathVariable Long stewardId,
            @PathVariable @DateTimeFormat(pattern = DATE_TIME_FORMAT) Date from,
            @PathVariable @DateTimeFormat(pattern = DATE_TIME_FORMAT) Date to) {
        return flightService.isStewardAvailableForFlight(stewardId, from, to, null);
    }

    @RequestMapping(value = "/{stewardId}/{from}/{to}/{flightId}", method = RequestMethod.GET)
    public boolean isStewardAvailableForFlightInclusive(
            @PathVariable Long stewardId,
            @PathVariable @DateTimeFormat(pattern = DATE_TIME_FORMAT) Date from,
            @PathVariable @DateTimeFormat(pattern = DATE_TIME_FORMAT) Date to,
            @PathVariable Long flightId) {
        return flightService.isStewardAvailableForFlight(stewardId, from, to, flightId);
    }
}
