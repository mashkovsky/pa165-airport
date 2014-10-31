package cz.muni.fi.pa165.airport.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Zdenek Kanovsky
 */
@NamedQueries({
        @NamedQuery(
                name = Flight.QUERY_IS_PLANE_AVAILABLE4FLIGHT,
                query = "SELECT COUNT(f) FROM Flight f " +
                        "JOIN f.plane p " +
                        "WHERE p.id = :planeId " +
                        "AND (" +
                            "((f.departure BETWEEN :fromT AND :toT) OR " +
                            "(f.arrival BETWEEN :fromT AND :toT) OR " +
                            "(f.departure <= :fromT AND f.arrival >= :toT))" +
                        ") "
        ),
        // Do not count actual flight
        @NamedQuery(
                name = Flight.QUERY_IS_PLANE_AVAILABLE4FLIGHT_EXCLUSIVE,
                query = "SELECT COUNT(f) FROM Flight f " +
                        "JOIN f.plane p " +
                        "WHERE p.id = :planeId " +
                        "AND (" +
                        "((f.departure BETWEEN :fromT AND :toT) OR " +
                        "(f.arrival BETWEEN :fromT AND :toT) OR " +
                        "(f.departure <= :fromT AND f.arrival >= :toT))" +
                        ") " +
                        "AND f.id != :flightId"
        ),
        @NamedQuery(
                name = Flight.QUERY_IS_STEWARD_AVAILABLE4FLIGHT,
                query = "SELECT COUNT(f) FROM Flight f " +
                        "JOIN f.stewards s " +
                        "WHERE s.id = :stewardId " +
                        "AND (" +
                            "((f.departure BETWEEN :fromT AND :toT) OR " +
                            "(f.arrival BETWEEN :fromT AND :toT) OR " +
                            "(f.departure <= :fromT AND f.arrival >= :toT))" +
                        ") "
        ),
        // Do not count actual flight
        @NamedQuery(
                name = Flight.QUERY_IS_STEWARD_AVAILABLE4FLIGHT_EXCLUSIVE,
                query = "SELECT COUNT(f) FROM Flight f " +
                        "JOIN f.stewards s " +
                        "WHERE s.id = :stewardId " +
                        "AND (" +
                        "((f.departure BETWEEN :fromT AND :toT) OR " +
                        "(f.arrival BETWEEN :fromT AND :toT) OR " +
                        "(f.departure <= :fromT AND f.arrival >= :toT))" +
                        ") " +
                        "AND f.id != :flightId"
        )
})
@Entity
@Table(name = "FLIGHT")
public class Flight implements Serializable {

    public static final String QUERY_IS_PLANE_AVAILABLE4FLIGHT = "isPlaneAvailable4Flight";
    public static final String QUERY_IS_PLANE_AVAILABLE4FLIGHT_EXCLUSIVE = "isPlaneAvailable4FlightExclusive";
    public static final String QUERY_IS_STEWARD_AVAILABLE4FLIGHT = "isStewardAvailable4Flight";
    public static final String QUERY_IS_STEWARD_AVAILABLE4FLIGHT_EXCLUSIVE = "isStewardAvailable4FlightExclusive";


    private Long id;
    private Date departure;
    private Date arrival;
    private Destination origin;
    private Destination destination;
    private Plane plane;
    private List<Steward> stewards;
    
    // id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
 
    // departure
    @Column(name = "DEPARTURE")
    public Date getDeparture() {
        return departure;
    }
    public void setDeparture(Date departure) {
        this.departure = departure;
    }
    
    // arrival
    @Column(name = "ARRIVAL")
    public Date getArrival() {
        return arrival;
    }
    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }
    
    // origin
    @OneToOne
    @JoinColumn(name="ORIGIN", nullable=false)
    public Destination getOrigin() {
        return origin;
    }
    public void setOrigin(Destination origin) {
        this.origin = origin;
    }
    
    // destination
    @OneToOne
    @JoinColumn(name="DESTINATION", nullable=false)
    public Destination getDestination() {
        return destination;
    }
    public void setDestination(Destination destination) {
        this.destination = destination;
    }
    
    // plane
    @OneToOne()
    @JoinColumn(name="PLANE_ID", nullable=false)
    public Plane getPlane() {
        return plane;
    }
    public void setPlane(Plane plane) {
        this.plane = plane;
    }
    
    // stewards
    @ManyToMany
    @JoinColumn(name = "STEWARD_ID", nullable = false)
    public List<Steward> getStewards() {
        return stewards;
    }

    public void setStewards(List<Steward> stewards) {
        this.stewards = stewards;
    }
    
    
    /**
     * Default constructor
     */
    public Flight() {
        this.stewards = new ArrayList<Steward>();
    }
    
    /**
     * Adds new steward
     * 
     * @param steward new steward
     * @return true if set of stewards contained the specified steward
     */
    public boolean addSteward(Steward steward) {
        return this.stewards.add(steward);
    }
    
    /**
     * Removes specified steward
     * 
     * @param steward the steward to be removed
     * @return true if set of stewards contained the specified steward
     */
    public boolean removeSteward(Steward steward) {
        return this.stewards.remove(steward);
    }

    @Override
    public boolean equals(Object other) {
        if(other == null || !(other instanceof Flight)) { 
            return false; 
        } 
        else if(other == this) { 
            return true; 
        } 
        else { 
            Flight otherFlight = (Flight)other; 
            return this.id == otherFlight.id;
        }
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }

    @Override
    public String toString() {
        return "Flight #" + id + " : " +
                "departure='" + departure + "'" +
                ", arrival='" + arrival + "'" +
                ", origin='" + origin + "'" +
                ", destination='" + destination + "'" +
                ", plane='" + plane + "'" +
                ", stewards=" + stewards;
    }
    
}
