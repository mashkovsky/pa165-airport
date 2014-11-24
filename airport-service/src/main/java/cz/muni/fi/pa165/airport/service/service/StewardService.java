package cz.muni.fi.pa165.airport.service.service;

import cz.muni.fi.pa165.airport.api.dto.StewardDTO;
import cz.muni.fi.pa165.airport.api.service.IStewardService;
import cz.muni.fi.pa165.airport.dao.dao.IStewardDAO;
import cz.muni.fi.pa165.airport.dao.entity.Steward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Methods of this class can throw subclasses of
 * org.springframework.dao.DataAccessException if error occurs on persistence
 * layer
 *
 * @author Matej Chrenko
 */
@Service
@Transactional
public class StewardService extends ConversionAware implements IStewardService {

    @Autowired
    private IStewardDAO stewardDAO;

    @Override
    public void createSteward(StewardDTO steward) {
        if (steward.getId() != null) {
            throw new IllegalArgumentException("Steward ID is not null");
        }
        Steward entity = mapper.map(steward, Steward.class);
        stewardDAO.create(entity);
        steward.setId(entity.getId());

    }

    @Override
    public void updateSteward(StewardDTO steward) {
        if (steward.getId() == null) {
            throw new IllegalArgumentException("Steward ID is null");
        }
        Steward entity = mapper.map(steward, Steward.class);
        stewardDAO.update(entity);
    }

    @Override
    public void deleteSteward(Long stewardId) {
        if (stewardId == null) {
            throw new IllegalArgumentException("Steward ID is null");
        }
        stewardDAO.delete(stewardId);
    }

    @Override
    public List<StewardDTO> getAllStewards() {
        return map(stewardDAO.getAll(), StewardDTO.class);
    }

    @Override
    public StewardDTO getSteward(Long stewardId) {
        if (stewardId == null) {
            throw new IllegalArgumentException("Steward ID is null");
        }
        Steward steward = stewardDAO.find(stewardId);
        return steward == null ? null : mapper.map(steward, StewardDTO.class);
    }

}
