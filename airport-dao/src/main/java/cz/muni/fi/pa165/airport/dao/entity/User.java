package cz.muni.fi.pa165.airport.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Matej Chrenko
 */
@Entity
@Table(name = "USER")
public class User extends CommonEntity {

    private String name;
    private String email;
    private String token;
    private String password;

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String type) {
        this.email = type;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "TOKEN")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public String toString() {
        return "Plane{"
                + "id=" + getId()
                + ", Name='" + name + '\''
                + ", Email='" + email + '\''
                + '}';
    }

}
