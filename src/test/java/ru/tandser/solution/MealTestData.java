package ru.tandser.solution;

import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.util.Matcher;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class MealTestData {

    public static List<Meal> meals;
    public static Meal       newMeal;
    public static Meal       duplicateMeal;
    public static Meal       invalidDateTimeMeal;
    public static Meal       invalidDescriptionMeal;
    public static Meal       invalidCaloriesMeal;

    public static LocalDateTime from;
    public static LocalDateTime to;

    public static final Matcher<Meal> MEAL_MATCHER = new Matcher<>((expected, actual) ->
            expected == actual || (Objects.equals(expected.getDateTime(),    actual.getDateTime())    &&
                                   Objects.equals(expected.getDescription(), actual.getDescription()) &&
                                   Objects.equals(expected.getCalories(),    actual.getCalories())));

    private MealTestData() {}

    public static void loadMocks() throws Exception {

    }
}