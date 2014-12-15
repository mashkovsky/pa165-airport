package cz.muni.fi.pa165.airport.cli.utils;


import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * JSON (de)serializing utils
 *
 * @author Mariia Schevchenko
 */
public final class JsonUtils {

    private JsonUtils() {
        // see Bloch, Java Effective 2, Item3
        throw new AssertionError();
    }

    /**
     * Serialize object to JSON
     *
     * @param obj object to serialize
     * @param <T> object type
     * @return string representation of JSON
     * @throws RuntimeException when serialization fails
     */
    public static <T> String toJsonString(T obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert object = " + obj + " to JSON", e);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert object = " + obj + " to JSON", e);
        }
    }

    /**
     * Deserialize JSON to object
     *
     * @param jsonString json string
     * @param clazz object class
     * @param <T> object type
     * @return deserialized object
     * @throws RuntimeException when deserialization fails
     */
    public static <T> T fromJson(final String jsonString, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(jsonString, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON = " + jsonString + " to object of type = " + clazz, e);
        }
    }
}
