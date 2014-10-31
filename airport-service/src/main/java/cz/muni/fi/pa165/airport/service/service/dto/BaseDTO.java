package cz.muni.fi.pa165.airport.service.service.dto;

import java.io.Serializable;

/**
 * Common class for all DTO objects. Will ensure that DTO is serializable and has ID attribute
 *
 * @author Mariia Schevchenko
 */
public abstract class BaseDTO implements Serializable {

    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
