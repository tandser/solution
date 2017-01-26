package ru.tandser.solution.util;

import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class LocalDateTimeFieldHandler extends GeneralizedFieldHandler {

    private static String pattern;

    @Override
    public void setConfiguration(Properties config) throws ValidityException {
        pattern = config.getProperty("date-time-format");
    }

    @Override
    public Class getFieldType() {
        return LocalDateTime.class;
    }

    @Override
    public Object convertUponGet(Object value) {
        return value != null
                ? ((LocalDateTime) value).format(DateTimeFormatter.ofPattern(pattern))
                : "null";
    }

    @Override
    public Object convertUponSet(Object value) {
        LocalDateTime result = null;

        try {
            result = LocalDateTime.parse((String) value, DateTimeFormatter.ofPattern(pattern));
        } catch (Exception ignored) {}

        return result;
    }
}