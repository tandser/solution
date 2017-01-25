package ru.tandser.solution;

import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.util.Matcher;

import java.util.Objects;

public class MealTestData {

    public static final Matcher<Meal> MEAL_MATCHER = new Matcher<>((expected, actual) ->
            expected == actual || (Objects.equals(expected.getId(),          actual.getId())          &&
                                   Objects.equals(expected.getDateTime(),    actual.getDateTime())    &&
                                   Objects.equals(expected.getDescription(), actual.getDescription()) &&
                                   Objects.equals(expected.getCalories(),    actual.getCalories())));
}