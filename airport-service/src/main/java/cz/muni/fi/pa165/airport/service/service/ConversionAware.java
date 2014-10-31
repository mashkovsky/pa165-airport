package cz.muni.fi.pa165.airport.service.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Map list of objects of type {@code K} to another list of object of type {@code T}.
     * Same as dozer mapper.map but works with lists
     *
     * @param fromList list to map from
     * @param toClass type of object to map to
     * @param <K> input type of objects in list
     * @param <T> output type of objects in list
     * @return conversed list of objects
     */
    protected <K, T> List<T> map(List<K> fromList, Class<T> toClass) {
        List<T> result = new ArrayList<T>();
        for (K item : fromList) {
            result.add(mapper.map(item, toClass));
        }

        return result;
    }
}
