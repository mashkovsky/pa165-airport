package cz.muni.fi.pa165.airport.service.service;

import cz.muni.fi.pa165.airport.service.service.dto.StewardDTO;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Matej Chrenko
 */
@Transactional
public interface IStewardService {
    /**
     * Create new steward
     *
     * @param steward steward to create
     */
    void createSteward(StewardDTO steward);
    
    /**
     * Update existing steward identified by {@code id} from system
     *
     * @param steward steward to update
     * @throws IllegalArgumentException if {@code steward.id} is {@code null}
     */
    void updateSteward(StewardDTO steward);
    
    /**
     * Delete existing steward identified by {@code id} from system
     *
     * @param stewardId steward unique id
     * @throws IllegalArgumentException if {@code steward.id} is {@code null}
     */
    void deleteSteward(Long stewardId);
    
    /**
     * Get all stewards ordered by name
     *
     * @return list of all stewards or empty list if no stewards exist
     */
    List<StewardDTO> getAllStewards();
    
    /**
     * Get steward by {@code stewardId}
     *
     * @param stewardId steward unique id
     * @return steward or {@code null} if no such steward exist
     * @throws IllegalArgumentException if {@code stewardId} is {@code null}
     */
    StewardDTO getSteward(Long stewardId);
}
