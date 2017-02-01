package ru.tandser.solution.web.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    private DateTimeFormatter formatter;

    public LocalDateTimeConverter(String pattern) {
        formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public LocalDateTime convert(String source) {
        LocalDateTime result = null;

        try {
            result = LocalDateTime.parse(source, formatter);
        } catch (Exception ignored) {}

        return result;
    }
}