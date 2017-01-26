package ru.tandser.solution;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.XMLContext;
import org.springframework.util.ResourceUtils;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.util.Matcher;
import ru.tandser.solution.util.Meals;

import java.io.FileReader;
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

    private MealTestData() {}

    public static final Matcher<Meal> MEAL_MATCHER = new Matcher<>((expected, actual) ->
            expected == actual || (Objects.equals(expected.getDateTime(),    actual.getDateTime())    &&
                                   Objects.equals(expected.getDescription(), actual.getDescription()) &&
                                   Objects.equals(expected.getCalories(),    actual.getCalories())));

    public static void loadMocks() throws Exception {
        FileReader reader = new FileReader(ResourceUtils.getFile("classpath:mocks/meals.xml"));

        Mapping mapping = new Mapping();
        mapping.loadMapping(ResourceUtils.getURL("classpath:castor/mapping.xml"));

        XMLContext xmlContext = new XMLContext();
        xmlContext.addMapping(mapping);

        Unmarshaller unmarshaller = xmlContext.createUnmarshaller();
        unmarshaller.setClass(Meals.class);

        Meals mocks = (Meals) unmarshaller.unmarshal(reader);

        reader.close();

        meals                  = mocks.getMeals().subList(0, 12);
        newMeal                = mocks.getMeals().get(12);
        duplicateMeal          = mocks.getMeals().get(13);
        invalidDateTimeMeal    = mocks.getMeals().get(14);
        invalidDescriptionMeal = mocks.getMeals().get(15);
        invalidCaloriesMeal    = mocks.getMeals().get(16);

        from = meals.get(0).getDateTime();
        to   = meals.get(5).getDateTime();
    }
}