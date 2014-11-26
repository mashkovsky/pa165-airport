package cz.muni.fi.pa165.airport.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Common class for all DTO objects. Will ensure that DTO is serializable and has ID attribute
 *
 * @author Mariia Schevchenko
 */
public abstract class BaseDTO implements Serializable {

    private Long id;
    private List<String> errorCodes = new ArrayList<String>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List<String> errorCodes) {
        this.errorCodes = errorCodes;
    }
}
