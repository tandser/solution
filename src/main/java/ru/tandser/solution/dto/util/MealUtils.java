package ru.tandser.solution.dto.util;

import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.dto.MealWithExcess;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MealUtils {

    private MealUtils() {}

    public static List<MealWithExcess> toMealWithExcess(List<Meal> meals, LocalTime from, LocalTime to, int normOfCalories) {
        Map<LocalDate, Integer> caloriesPerDay = meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));

        return meals.stream()
                .filter(meal -> DateTimeUtils.isBetween(meal.getTime(), from, to))
                .map(meal -> toMealWithExcess(meal, caloriesPerDay.get(meal.getDate()) > normOfCalories))
                .collect(Collectors.toList());
    }

    public static List<MealWithExcess> toMealWithExcess(List<Meal> meals, int normOfCalories) {
        return toMealWithExcess(meals, LocalTime.MIN, LocalTime.MAX, normOfCalories);
    }

    private static MealWithExcess toMealWithExcess(Meal meal, boolean excess) {
        return new MealWithExcess(meal, excess);
    }
}