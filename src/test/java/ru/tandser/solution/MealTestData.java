package ru.tandser.solution;

import org.springframework.util.ResourceUtils;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.util.Matcher;
import ru.tandser.solution.web.json.JacksonObjectMapper;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
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
        BufferedInputStream input = new BufferedInputStream(
                new FileInputStream(ResourceUtils.getFile("classpath:mocks/meals.json")));

        List<Meal> mocks = JacksonObjectMapper.getInstance()
                .readerFor(Meal.class).<Meal>readValues(input).readAll();

        input.close();

        meals                  = mocks.subList(0, 12);
        newMeal                = mocks.get(12);
        duplicateMeal          = mocks.get(13);
        invalidDateTimeMeal    = mocks.get(14);
        invalidDescriptionMeal = mocks.get(15);
        invalidCaloriesMeal    = mocks.get(16);

        from = LocalDateTime.of(2017, 1, 9,  10, 0);
        to   = LocalDateTime.of(2017, 1, 10, 19, 0);
    }
}