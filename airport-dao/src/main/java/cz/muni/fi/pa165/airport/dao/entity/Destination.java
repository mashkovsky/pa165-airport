package cz.muni.fi.pa165.airport.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Mariia Shevchenko
 */
@NamedQueries({
        @NamedQuery(
                name = Destination.QUERY_IS_NOT_USED_IN_FLIGHTS,
                query = "SELECT COUNT(f) FROM Flight f " +
                        "JOIN f.origin o " +
                        "JOIN f.destination d " +
                        "WHERE o.id = :destinationId OR d.id = :destinationId "
        )
})
@Entity
@Table(name = "DESTINATION")
public class Destination extends CommonEntity {
    
    public static final String QUERY_IS_NOT_USED_IN_FLIGHTS = "isNotUsedInFlights";

    private String country;
    private String city;


    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "id=" + getId() +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
