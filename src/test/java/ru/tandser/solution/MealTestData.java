package ru.tandser.solution;

import org.springframework.util.ResourceUtils;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.dto.MealWithExcess;
import ru.tandser.solution.util.Matcher;
import ru.tandser.solution.web.json.JsonConverter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.tandser.solution.UserTestData.user;

public class MealTestData {

    public static List<Meal> meals;
    public static List<Meal> betweenMeals;
    public static List<Meal> reverseOrderMeals;
    public static List<Meal> updatedMeals;

    public static Meal existingMeal;
    public static Meal newMeal;
    public static Meal notNewMeal;
    public static Meal nonExistentMeal;
    public static Meal conflictMeal;
    public static Meal duplicateMeal;
    public static Meal invalidDateTimeMeal;
    public static Meal invalidDescriptionMeal;
    public static Meal invalidCaloriesMeal;

    public static LocalDateTime from;
    public static LocalDateTime to;

    public static List<MealWithExcess> mealsWithExcess;
    public static List<MealWithExcess> betweenMealsWithExcess;

    public static final Matcher<Meal> MEAL_MATCHER = new Matcher<>(Meal.class, (expected, actual) ->
            expected == actual || (Objects.equals(expected.getDateTime(),    actual.getDateTime())    &&
                                   Objects.equals(expected.getDescription(), actual.getDescription()) &&
                                   Objects.equals(expected.getCalories(),    actual.getCalories())));

    public static final Matcher<MealWithExcess> MEAL_WITH_EXCESS_MATCHER = new Matcher<>(MealWithExcess.class, (expected, actual) ->
            expected == actual || (Objects.equals(expected.getDateTime(),    actual.getDateTime())    &&
                                   Objects.equals(expected.getDescription(), actual.getDescription()) &&
                                   Objects.equals(expected.getCalories(),    actual.getCalories())    &&
                                   Objects.equals(expected.getExcess(),      actual.getExcess())));

    private MealTestData() {}

    public static void loadMocks() throws Exception {
        List<Meal> mocks = JsonConverter.fromJsonToList(ResourceUtils.getFile("classpath:mocks/meals.json"), Meal.class);

        meals                  = mocks.subList(0, 12);
        betweenMeals           = mocks.subList(0, 6);
        updatedMeals           = mocks.subList(1, 12);
        existingMeal           = mocks.get(0);
        newMeal                = mocks.get(12);
        notNewMeal             = mocks.get(13);
        nonExistentMeal        = mocks.get(14);
        conflictMeal           = mocks.get(15);
        duplicateMeal          = mocks.get(16);
        invalidDateTimeMeal    = mocks.get(17);
        invalidDescriptionMeal = mocks.get(18);
        invalidCaloriesMeal    = mocks.get(19);

        reverseOrderMeals = new ArrayList<>(meals);
        reverseOrderMeals.sort((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()));

        from = LocalDateTime.of(2017, 1, 9,  10, 0);
        to   = LocalDateTime.of(2017, 1, 10, 19, 0);

        mealsWithExcess        = MealWithExcess.convert(meals, user.getNormOfCalories());
        betweenMealsWithExcess = MealWithExcess.filter(betweenMeals, from.toLocalTime(), to.toLocalTime(), user.getNormOfCalories());
    }
}