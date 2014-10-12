package cz.muni.fi.pa165.airport.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.*;

/**
 * @author Zdenek Kanovsky
 */

@Entity
@Table(name = "FLIGHT")
public class Flight implements Serializable {
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
