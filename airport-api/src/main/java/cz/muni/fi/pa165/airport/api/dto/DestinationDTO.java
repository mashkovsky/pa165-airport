package cz.muni.fi.pa165.airport.api.dto;

/**
 * @author Zdenek Kanovsky
 */
public class DestinationDTO extends BaseDTO {

    private String country;
    private String city;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
