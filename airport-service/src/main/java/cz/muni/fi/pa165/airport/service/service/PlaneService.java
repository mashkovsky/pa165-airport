package cz.muni.fi.pa165.airport.service.service;

import cz.muni.fi.pa165.airport.api.dto.PlaneDTO;
import cz.muni.fi.pa165.airport.api.service.IPlaneService;
import cz.muni.fi.pa165.airport.dao.dao.IPlaneDAO;
import cz.muni.fi.pa165.airport.dao.entity.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Methods of this class can throw subclasses of org.springframework.dao.DataAccessException if error occurs on persistence layer
 * 
 * @author Jan Jilek
 */
@Service
@Transactional
public class PlaneService extends ConversionAware implements IPlaneService {
    
    @Autowired
    private IPlaneDAO planeDAO;
    
    @Override
    public void createPlane(PlaneDTO plane) {
        if (plane.getId() != null) {
            throw new IllegalArgumentException("Plane ID is not null");
        }
        if(plane.getCapacity() <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }

        Plane entity = mapper.map(plane, Plane.class);
        planeDAO.create(entity);
        plane.setId(entity.getId());
        
    }

    @Override
    public void updatePlane(PlaneDTO plane) {
        if (plane.getId() == null) {
            throw new IllegalArgumentException("Plane ID is null");
        }
        if(plane.getCapacity() <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }

        Plane entity = mapper.map(plane, Plane.class);
        planeDAO.update(entity);
    }
    
    @Override
    public void deletePlane(Long planeId) {
        if (planeId == null) {
            throw new IllegalArgumentException("Plane ID is null");
        }
        planeDAO.delete(planeId);
    }
    
    @Override
    public List<PlaneDTO> getAllPlanes() {
        return map(planeDAO.getAll(), PlaneDTO.class);
    }

    @Override
    public PlaneDTO getPlane(Long planeId) {
        if (planeId == null) {
            throw new IllegalArgumentException("Plane ID is null");
        }

        Plane plane = planeDAO.find(planeId);

        return plane == null ? null : mapper.map(plane, PlaneDTO.class);
    }
    
}
