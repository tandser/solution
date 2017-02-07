package ru.tandser.solution.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeUtil {

    private DateTimeUtil() {}

    public static <T extends Comparable<? super T>> boolean isBetween(T value, T from, T to) {
        return value.compareTo(from) >= 0 && value.compareTo(to) <= 0;
    }

    public static LocalDateTime atStartOfDay(LocalDateTime value) {
        return value != null
                ? LocalDateTime.of(value.toLocalDate(), LocalTime.MIN)
                : null;
    }

    public static LocalDateTime atEndOfDay(LocalDateTime value) {
        return value != null
                ? LocalDateTime.of(value.toLocalDate(), LocalTime.MAX)
                : null;
    }
}