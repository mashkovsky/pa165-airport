package cz.muni.fi.pa165.airport.cli.rest;

import com.mashape.unirest.http.Headers;
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
import java.util.HashMap;
import java.util.List;

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
    private static final String HEADER_SET_COOKIE = "set-cookie";

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

    private String jSessionIdCookie;


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

    private HashMap<String, String> getHeaders() {
        return new HashMap<String, String>(){{
            put("accept", JSON_CONTENT_TYPE);
            put("Content-Type", JSON_CONTENT_TYPE);
            if (jSessionIdCookie != null) {
                put("Cookie", jSessionIdCookie + ";");
            }

        }};
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
                .headers(getHeaders())
                .body(JsonUtils.toJsonString(obj))
                .asJson();

            return (T) JsonUtils.fromJson(response.getBody().toString(), clazz);
        } catch (UnirestException e) {
            throw new RuntimeException("Failed to create object = " + obj + " of type = " + clazz + ". " + e.getMessage(), e);
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
                .headers(getHeaders())
                .body(JsonUtils.toJsonString(obj))
                .asJson();

            return (T) JsonUtils.fromJson(response.getBody().toString(), clazz);
        } catch (UnirestException e) {
            throw new RuntimeException("Failed to update object = " + obj + " of type = " + clazz + ". " + e.getMessage(), e);
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
                    .headers(getHeaders())
                    .asJson();

            return JsonUtils.fromJson(response.getBody().toString(), DeleteResponseDTO.class);
        } catch (UnirestException e) {
            throw new RuntimeException("Failed to delete object with ID = " + id + ". " + e.getMessage(), e);
        }
    }

    /**
     * Fetches object using GET method by ID
     * @param id objects ID
     *
     * @return fetched object
     */
    public <T extends BaseDTO> T get(final Long id, Class<? extends BaseDTO> clazz) {
        try {
            HttpResponse<JsonNode> response = Unirest.get(buildUrl(id, clazz))
                    .headers(getHeaders())
                    .asJson();

            return (T) JsonUtils.fromJson(response.getBody().toString(), clazz);
        } catch (UnirestException e) {
            throw new RuntimeException("Failed to fetch object with ID = " + id + ". " + e.getMessage(), e);
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
                    .headers(getHeaders())
                    .asJson();

            return JsonUtils.fromJson(response.getBody().toString(), List.class);
        } catch (UnirestException e) {
            throw new RuntimeException("Failed to fetch all objects. " + e.getMessage(), e);
        }
    }

    /**
     * Logins to application with hardcoded username and email.
     * Every other request will use JSESSION ID for authentization
     *
     * @return true if login is success, false otherwise.
     */
    public boolean login() {
        try {
            HttpResponse<String> response = Unirest.post("http://localhost:8080/pa165/j_spring_security_check")
                    .field("j_username", "rest@airport.com")
                    .field("j_password", "rest")
                    .asString();

            Headers headers = response.getHeaders();

            if (headers.containsKey(HEADER_SET_COOKIE)) {
                for (String cookie : headers.get(HEADER_SET_COOKIE)) {
                    if (cookie.startsWith("JSESSIONID=")) {
                        jSessionIdCookie = cookie;
                        if (jSessionIdCookie.contains(";")) {
                            jSessionIdCookie = jSessionIdCookie.substring(0, jSessionIdCookie.indexOf(";"));
                        }
                        break;
                    }
                }
            }

            return (jSessionIdCookie != null);
        } catch (UnirestException e) {
            throw new RuntimeException("Failed to login. " + e.getMessage(), e);
        }
    }

    /**
     * Shutdown client.
     */
    public void shutdown() {
        try {
            Unirest.shutdown();
        } catch (IOException e) {
            throw new RuntimeException("Failed to shutdown client. " + e.getMessage(), e);
        }
    }
}
