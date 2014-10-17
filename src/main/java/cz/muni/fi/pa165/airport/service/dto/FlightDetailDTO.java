package cz.muni.fi.pa165.airport.service.dto;

import java.util.List;

/**
 * @author Mariia Schevchenko
 */
public class FlightDetailDTO extends FlightMinimalDTO {

    private int capacity;
    private PlaneDTO plane;
    private List<StewardDTO> stewards;


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public PlaneDTO getPlane() {
        return plane;
    }

    public void setPlane(PlaneDTO plane) {
        this.plane = plane;
    }

    public List<StewardDTO> getStewards() {
        return stewards;
    }

    public void setStewards(List<StewardDTO> stewards) {
        this.stewards = stewards;
    }
}
