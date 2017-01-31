package ru.tandser.solution.web.json;

import java.io.File;
import java.util.List;

public class JsonConverter {

    public static String toJson(Object obj) {
        try {
            return JacksonObjectMapper.getInstance().writer().writeValueAsString(obj);
        } catch (Exception exc) {
            throw new IllegalStateException(exc);
        }
    }

    public static <T> T fromJson(String json, Class<T> type) {
        try {
            return JacksonObjectMapper.getInstance().readerFor(type).readValue(json);
        } catch (Exception exc) {
            throw new IllegalArgumentException(exc);
        }
    }

    public static <T> T fromJson(File json, Class<T> type) {
        try {
            return JacksonObjectMapper.getInstance().readerFor(type).readValue(json);
        } catch (Exception exc) {
            throw new IllegalArgumentException(exc);
        }
    }

    public static <T> List<T> fromJsonToList(String json, Class<T> type) {
        try {
            return JacksonObjectMapper.getInstance().readerFor(type).<T>readValues(json).readAll();
        } catch (Exception exc) {
            throw new IllegalArgumentException(exc);
        }
    }

    public static <T> List<T> fromJsonToList(File json, Class<T> type) {
        try {
            return JacksonObjectMapper.getInstance().readerFor(type).<T>readValues(json).readAll();
        } catch (Exception exc) {
            throw new IllegalArgumentException(exc);
        }
    }
}