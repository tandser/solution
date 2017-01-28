package ru.tandser.solution;

import org.springframework.util.ResourceUtils;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.util.Matcher;
import ru.tandser.solution.web.json.JacksonObjectMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MealTestData {

    public static List<Meal> meals;
    public static List<Meal> mealsReverseOrder;
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
        List<Meal> mocks = JacksonObjectMapper.getInstance()
                .readerFor(Meal.class).<Meal>readValues(ResourceUtils.getFile("classpath:mocks/meals.json")).readAll();

        meals                  = mocks.subList(0, 12);
        newMeal                = mocks.get(12);
        duplicateMeal          = mocks.get(13);
        invalidDateTimeMeal    = mocks.get(14);
        invalidDescriptionMeal = mocks.get(15);
        invalidCaloriesMeal    = mocks.get(16);

        mealsReverseOrder = new ArrayList<>(meals);
        mealsReverseOrder.sort((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()));

        from = LocalDateTime.of(2017, 1, 9,  10, 0);
        to   = LocalDateTime.of(2017, 1, 10, 19, 0);
    }
}