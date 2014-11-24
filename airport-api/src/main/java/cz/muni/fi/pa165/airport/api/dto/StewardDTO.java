package cz.muni.fi.pa165.airport.api.dto;

/**
 * @author Mariia Shevchenko
 */
public class StewardDTO extends BaseDTO {

    private String firstName;
    private String lastName;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
