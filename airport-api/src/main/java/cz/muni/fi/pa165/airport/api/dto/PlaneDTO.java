package cz.muni.fi.pa165.airport.api.dto;

/**
 * @author Matej Chrenko
 */
public class PlaneDTO extends BaseDTO {

    private String name;
    private String type;
    private int capacity;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
