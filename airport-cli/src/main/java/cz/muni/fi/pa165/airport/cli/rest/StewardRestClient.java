package cz.muni.fi.pa165.airport.cli.rest;

import cz.muni.fi.pa165.airport.api.dto.StewardDTO;

/**
 * REST client for StewardDTO
 *
 * @author Mariia Schevchenko
 */
public class StewardRestClient extends RestClient<StewardDTO> {

    public StewardRestClient(final String serverUrl) {
        super(serverUrl);
    }

    @Override
    protected ObjectType getObjectType() {
        return ObjectType.STEWARD;
    }
}
