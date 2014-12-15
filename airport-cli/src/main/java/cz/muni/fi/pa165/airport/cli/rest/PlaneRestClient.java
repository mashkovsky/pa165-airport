package cz.muni.fi.pa165.airport.cli.rest;

import cz.muni.fi.pa165.airport.api.dto.PlaneDTO;

/**
 * REST client for PlaneDTO
 *
 * @author Mariia Schevchenko
 */
public class PlaneRestClient extends RestClient<PlaneDTO> {

    public PlaneRestClient(final String serverUrl) {
        super(serverUrl);
    }

    @Override
    protected RestClient.ObjectType getObjectType() {
        return ObjectType.PLANE;
    }
}
