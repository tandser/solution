package ru.tandser.solution.dto.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeUtils {

    private DateTimeUtils() {}

    public static <T extends Comparable<? super T>> boolean isBetween(T value, T from, T to) {
        return value.compareTo(from) >= 0 && value.compareTo(to) <= 0;
    }

    public static LocalDateTime withMinTime(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MIN);
    }

    public static LocalDateTime withMaxTime(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MAX);
    }
}