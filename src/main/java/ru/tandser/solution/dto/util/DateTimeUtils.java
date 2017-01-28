package ru.tandser.solution.dto.util;

public class DateTimeUtils {

    private DateTimeUtils() {}

    public static <T extends Comparable<? super T>> boolean isBetween(T value, T from, T to) {
        return value.compareTo(from) >= 0 && value.compareTo(to) <= 0;
    }
}