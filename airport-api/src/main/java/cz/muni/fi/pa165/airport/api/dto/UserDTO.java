package cz.muni.fi.pa165.airport.api.dto;

/**
 * @author Mariia Schevchenko
 */
public class UserDTO extends BaseDTO {

    private String password;
    private String email;
    private String name;
    private boolean privileged;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrivileged() {
        return privileged;
    }

    public void setPrivileged(boolean privileged) {
        this.privileged = privileged;
    }
}
