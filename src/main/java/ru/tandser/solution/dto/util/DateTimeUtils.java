package ru.tandser.solution.dto.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeUtils {

    private DateTimeUtils() {}

    public static <T extends Comparable<? super T>> boolean isBetween(T value, T from, T to) {
        return value.compareTo(from) >= 0 && value.compareTo(to) <= 0;
    }

    public static LocalDateTime atStartOfDay(LocalDateTime dateTime) {
        return dateTime != null
                ? LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MIN)
                : null;
    }

    public static LocalDateTime atEndOfDay(LocalDateTime dateTime) {
        return dateTime != null
                ? LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MAX)
                : null;
    }
}