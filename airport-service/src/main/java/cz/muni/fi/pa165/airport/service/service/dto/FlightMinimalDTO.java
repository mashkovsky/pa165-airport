package cz.muni.fi.pa165.airport.service.service.dto;

import java.util.Date;

/**
 * @author Mariia Schevchenko
 */
public class FlightMinimalDTO extends BaseDTO {

    private DestinationDTO origin;
    private DestinationDTO destination;
    private Date departure;
    private Date arrival;


    public DestinationDTO getOrigin() {
        return origin;
    }

    public void setOrigin(DestinationDTO origin) {
        this.origin = origin;
    }

    public DestinationDTO getDestination() {
        return destination;
    }

    public void setDestination(DestinationDTO destination) {
        this.destination = destination;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }
}
