/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.airport.service.service;

import cz.muni.fi.pa165.airport.service.service.dto.PlaneDTO;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jan Jilek
 */
@Transactional
public interface IPlaneService {
    /**
     * Create new plane
     *
     * @param plane plane to create
     */
    void createPlane(PlaneDTO plane);
    
    /**
     * Update existing plane identified by {@code id} from system
     *
     * @param plane plane to update
     * @throws IllegalArgumentException if {@code plane.id} is {@code null}
     */
    void updatePlane(PlaneDTO plane);
    
    /**
     * Delete existing plane identified by {@code id} from system
     *
     * @param planeId plane unique id
     * @throws IllegalArgumentException if {@code plane.id} is {@code null}
     */
    void deletePlane(Long planeId);
    
    /**
     * Get all planes ordered by name
     *
     * @return list of all planes or empty list if no planes exist
     */
    List<PlaneDTO> getAllPlanes();
    
    /**
     * Get plane by {@code planeId}
     *
     * @param planeId plane unique id
     * @return plane or {@code null} if no such plane exist
     * @throws IllegalArgumentException if {@code planeId} is {@code null}
     */
    PlaneDTO getPlane(Long planeId);
}
