package cz.muni.fi.pa165.airport.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Extend this class to get access to Dozer conversion mapper
 *
 * @author Mariia Schevchenko
 */
public abstract class ConversionAware {

    @Autowired
    @Qualifier("dozerBeanMapper")
    protected Mapper mapper;


    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }
}
