package cz.muni.fi.pa165.airport.service.service;

import cz.muni.fi.pa165.airport.api.dto.DestinationDTO;
import cz.muni.fi.pa165.airport.api.service.IDestinationService;
import cz.muni.fi.pa165.airport.dao.dao.IDestinationDAO;
import cz.muni.fi.pa165.airport.dao.entity.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Methods of this class can throw subclasses of org.springframework.dao.DataAccessException if error occurs on
 * persistence layer
 * 
 * @author Zdenek Kanovsky
 */
@Service
@Transactional
public class DestinationService extends ConversionAware implements IDestinationService {
    
    @Autowired
    private IDestinationDAO destinationDAO;
    
    @Override
    public void createDestination(DestinationDTO destination) {

        // id has to be null
        if (destination.getId() != null) {
            throw new IllegalArgumentException("Destination ID is not null");
        }

        Destination entity = mapper.map(destination, Destination.class);
        destinationDAO.create(entity);
        destination.setId(entity.getId());
    }
    
    @Override
    public void updateDestination(DestinationDTO destination) {
        
        // id has to be not null
        if (destination.getId() == null) {
            throw new IllegalArgumentException("Destination ID is null");
        }

        Destination entity = mapper.map(destination, Destination.class);
        destinationDAO.update(entity);
    }
    
    @Override
    public void deleteDestination(Long destinationId) {
        
        if (destinationId == null) {
            throw new IllegalArgumentException("Destination ID is null");
        }
        /*
        if(destinationDAO.find(destinationId) == null) {
            throw new IllegalStateException("Destination of id = " + destinationId
                + " does not exist");
        }

        if (!destinationDAO.isNotUsedInFlights(destinationId)) {
                throw new IllegalStateException("Destination of id = " + destinationId
                        + " is already used in any flight as origin or destination");
        }
        */
        destinationDAO.delete(destinationId);
    }
    
    @Override
    public List<DestinationDTO> getAllDestinations() {
        return map(destinationDAO.getAll(), DestinationDTO.class);
    }
    
    @Override
    public DestinationDTO getDestination(Long destinationId) {
        if (destinationId == null) {
            throw new IllegalArgumentException("DestinationId ID is null");
        }

        Destination destination = destinationDAO.find(destinationId);

        return destination == null ? null : mapper.map(destination, DestinationDTO.class);
    }
    
}
