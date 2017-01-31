package ru.tandser.solution.dto;

import com.google.common.base.MoreObjects;
import org.springframework.util.Assert;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.dto.util.DateTimeUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MealWithExcess {

    private Integer       id;
    private LocalDateTime dateTime;
    private String        description;
    private int           calories;
    private boolean       excess;

    public MealWithExcess() {}

    public MealWithExcess(Meal meal, boolean excess) {
        Assert.notNull(meal);

        id          = meal.getId();
        dateTime    = meal.getDateTime();
        description = meal.getDescription();
        calories    = meal.getCalories();
        this.excess = excess;
    }

    public static MealWithExcess valueOf(Meal meal, boolean excess) {
        return new MealWithExcess(meal, excess);
    }

    public static List<MealWithExcess> filter(List<Meal> meals, LocalTime from, LocalTime to, int normOfCalories) {
        Map<LocalDate, Integer> caloriesPerDay = meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));

        return meals.stream()
                .filter(meal -> DateTimeUtils.isBetween(meal.getTime(), from, to))
                .map(meal -> valueOf(meal, caloriesPerDay.get(meal.getDate()) > normOfCalories))
                .collect(Collectors.toList());
    }

    public static List<MealWithExcess> convert(List<Meal> meals, int normOfCalories) {
        return filter(meals, LocalTime.MIN, LocalTime.MAX, normOfCalories);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isExcess() {
        return excess;
    }

    public void setExcess(boolean excess) {
        this.excess = excess;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id",          id)
                .add("date_time",   dateTime)
                .add("description", description)
                .add("calories",    calories)
                .add("excess",      excess)
                .toString();
    }
}