package ru.tandser.solution.util.json;

import com.fasterxml.jackson.databind.ObjectReader;

import java.io.InputStream;
import java.util.List;

import static ru.tandser.solution.util.json.JacksonObjectMapper.getMapper;

public class JacksonUtil {

    public static <T> String writeValue(T object) {
        try {
            return getMapper().writeValueAsString(object);
        } catch (Exception exc) {
            throw new IllegalStateException(exc);
        }
    }

    public static <T> T readValue(String json, Class<T> type) {
        try {
            return getMapper().readValue(json, type);
        } catch (Exception exc) {
            throw new IllegalArgumentException(exc);
        }
    }

    public static <T> T readValue(InputStream input, Class<T> type) {
        try {
            return getMapper().readValue(input, type);
        } catch (Exception exc) {
            throw new IllegalArgumentException(exc);
        }
    }

    public static <T> List<T> readValues(String json, Class<T> type) {
        ObjectReader reader = getMapper().readerFor(type);

        try {
            return reader.<T>readValues(json).readAll();
        } catch (Exception exc) {
            throw new IllegalArgumentException(exc);
        }
    }

    public static <T> List<T> readValues(InputStream input, Class<T> type) {
        ObjectReader reader = getMapper().readerFor(type);

        try {
            return reader.<T>readValues(input).readAll();
        } catch (Exception exc) {
            throw new IllegalArgumentException(exc);
        }
    }
}