package cz.muni.fi.pa165.airport.cli.rest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import cz.muni.fi.pa165.airport.api.dto.BaseDTO;
import cz.muni.fi.pa165.airport.api.dto.DeleteResponseDTO;
import cz.muni.fi.pa165.airport.api.dto.PlaneDTO;
import cz.muni.fi.pa165.airport.api.dto.StewardDTO;
import cz.muni.fi.pa165.airport.cli.utils.JsonUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST client for airport entities. Remember to call #shutdown() after finishing your work.
 *
 * @author Mariia Schevchenko
 */
public class RestClient {

    /**
     * Content type of all messages sent or received
     */
    private static final String JSON_CONTENT_TYPE = "application/json";

    /**
     * Headers sent with all requests
     */
    private static final Map<String, String> HEADERS = Collections.unmodifiableMap(new HashMap<String, String>(){{
        put("accept", JSON_CONTENT_TYPE);
        put("Content-Type", JSON_CONTENT_TYPE);
    }});

    /**
     * Object type that is allowed to communicate through this REST client
     */
    static public enum ObjectType {
        PLANE("/planes", PlaneDTO.class),
        STEWARD("/stewards", StewardDTO.class);

        /**
         * URI to which REST requests will be transfered
         */
        private final String uri;
        /**
         * Class of object type
         */
        private final Class<?> clazz;

        ObjectType(String uri, Class<?> clazz) {
            this.uri = uri;
            this.clazz = clazz;
        }

        public String getUri() {
            return uri;
        }

        public Class<?> getClazz() {
            return clazz;
        }

        public static ObjectType getByClass(Class<?> clazz) {
            for (ObjectType objectType : values()) {
                if (objectType.clazz.equals(clazz)) {
                    return objectType;
                }
            }

            throw new IllegalArgumentException("Unknown class type = " + clazz);
        }
    }

    /**
     * Server URL to which client will communicate
     */
    private final String serverUrl;


    /**
     * @param serverUrl server URL to connect to
     */
    public RestClient(String serverUrl) {
        if (serverUrl == null) {
            throw new IllegalArgumentException("URL is null");
        }

        this.serverUrl = serverUrl;
    }

    /**
     * Creates URL based on actual object class
     */
    private String buildUrl(Class<? extends BaseDTO> clazz) {
        return serverUrl + ObjectType.getByClass(clazz).uri;
    }

    /**
     * Creates URL based on actual object class and ID
     */
    private String buildUrl(final long id, Class<? extends BaseDTO> clazz) {
        return buildUrl(clazz) + "/" + id;
    }

    /**
     * Sends POST request which will trigger object creation
     * @param obj object to create
     *
     * @return created object with pre-filled ID or errorCodes
     */
    public <T extends BaseDTO> T create(T obj, Class<? extends BaseDTO> clazz) {
        try {
            HttpResponse<JsonNode> response = Unirest.post(buildUrl(clazz))
                .headers(HEADERS)
                .body(JsonUtils.toJsonString(obj))
                .asJson();

            return (T) JsonUtils.fromJson(response.getBody().toString(), clazz);
        } catch (UnirestException e) {
            throw new RuntimeException("Failed to create object = " + obj + " of type = " + clazz, e);
        }
    }

    /**
     * Sends PUT request which will trigger object update
     * @param obj object to update
     *
     * @return updated object with pre-filled ID or errorCodes
     */
    public <T extends BaseDTO> T update(T obj, Class<? extends BaseDTO> clazz) {
        try {
            HttpResponse<JsonNode> response = Unirest.put(buildUrl(obj.getId(), clazz))
                .headers(HEADERS)
                .body(JsonUtils.toJsonString(obj))
                .asJson();

            return (T) JsonUtils.fromJson(response.getBody().toString(), clazz);
        } catch (UnirestException e) {
            throw new RuntimeException("Failed to update object = " + obj + " of type = " + clazz, e);
        }
    }

    /**
     * Sends DELETE request which will trigger object removal
     * @param id objects ID to delete
     *
     * @return info about deleted object
     */
    public DeleteResponseDTO delete(final Long id, Class<? extends BaseDTO> clazz) {
        try {
            HttpResponse<JsonNode> response = Unirest.delete(buildUrl(id, clazz))
                    .headers(HEADERS)
                    .asJson();

            return JsonUtils.fromJson(response.getBody().toString(), DeleteResponseDTO.class);
        } catch (UnirestException e) {
            throw new RuntimeException("Failed to delete object with ID = " + id, e);
        }
    }

    /**
     * Fetches object using GET method by ID
     * @param id objects ID
     *
     * @return fetched object or {@code null}
     */
    public <T extends BaseDTO> T get(final Long id, Class<? extends BaseDTO> clazz) {
        try {
            HttpResponse<JsonNode> response = Unirest.get(buildUrl(id, clazz))
                    .headers(HEADERS)
                    .asJson();

            T result = (T) JsonUtils.fromJson(response.getBody().toString(), clazz);
            return (result.getId() == null) ? null : result;
        } catch (UnirestException e) {
            throw new RuntimeException("Failed to fetch object with ID = " + id, e);
        }
    }

    /**
     * Fetches all objects
     *
     * @return fetched objects or empty list
     */
    public List getAll(Class<? extends BaseDTO> clazz) {
        try {
            HttpResponse<JsonNode> response = Unirest.get(buildUrl(clazz))
                    .headers(HEADERS)
                    .asJson();

            return JsonUtils.fromJson(response.getBody().toString(), List.class);
        } catch (UnirestException e) {
            throw new RuntimeException("Failed to fetch all objects", e);
        }
    }

    /**
     * Shutdown client.
     */
    public void shutdown() {
        try {
            Unirest.shutdown();
        } catch (IOException e) {
            throw new RuntimeException("Failed to shutdown client.", e);
        }
    }
}
