package cz.muni.fi.pa165.airport.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class Destination {
    
    public static final String QUERY_IS_NOT_USED_IN_FLIGHTS = "isNotUsedInFlights";
    
    private Long id;
    private String country;
    private String city;


    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Destination that = (Destination) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Destination{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
