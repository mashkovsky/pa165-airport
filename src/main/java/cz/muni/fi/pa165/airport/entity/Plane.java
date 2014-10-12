package cz.muni.fi.pa165.airport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Matej Chrenko
 */
@Entity
@Table(name = "PLANE")
public class Plane {

    private Long id;
    private String type;
    private String name;
    private Integer capacity;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CAPACITY")
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Plane)) {
            return false;
        } else if (other == this) {
            return true;
        } else {
            Plane plane = (Plane) other;
            return this.id == plane.id;
        }
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Plane{"
                + "id=" + id
                + ", Name='" + name + '\''
                + ", Type='" + type + '\''
                + ", Capacity='" + capacity + '\''
                + '}';
    }

}
