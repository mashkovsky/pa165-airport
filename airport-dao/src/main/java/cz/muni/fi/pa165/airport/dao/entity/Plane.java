package cz.muni.fi.pa165.airport.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Matej Chrenko
 */
@Entity
@Table(name = "PLANE")
public class Plane extends CommonEntity {

    private String type;
    private String name;
    private Integer capacity;


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
    public String toString() {
        return "Plane{"
                + "id=" + getId()
                + ", Name='" + name + '\''
                + ", Type='" + type + '\''
                + ", Capacity='" + capacity + '\''
                + '}';
    }

}
