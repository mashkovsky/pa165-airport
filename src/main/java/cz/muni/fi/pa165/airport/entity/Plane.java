package cz.muni.fi.pa165.airport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLANE")
public class Plane {
    
    // id
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;
}
