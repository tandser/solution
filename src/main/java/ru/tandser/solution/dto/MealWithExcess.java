package ru.tandser.solution.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
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

    @JsonCreator
    public MealWithExcess(@JsonProperty("id")          Integer       id,
                          @JsonProperty("dateTime")    LocalDateTime dateTime,
                          @JsonProperty("description") String        description,
                          @JsonProperty("calories")    int           calories,
                          @JsonProperty("excess")      boolean       excess) {

        this.id          = id;
        this.dateTime    = dateTime;
        this.description = description;
        this.calories    = calories;
        this.excess      = excess;
    }

    public MealWithExcess(Meal meal, boolean excess) {
        this(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }

    public static List<MealWithExcess> filter(List<Meal> meals, LocalTime from, LocalTime to, int normOfCalories) {
        Map<LocalDate, Integer> caloriesPerDay = meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));

        return meals.stream()
                .filter(meal -> DateTimeUtils.isBetween(meal.getTime(), from, to))
                .map(meal -> new MealWithExcess(meal, caloriesPerDay.get(meal.getDate()) > normOfCalories))
                .collect(Collectors.toList());
    }

    public static List<MealWithExcess> convert(List<Meal> meals, int normOfCalories) {
        return filter(meals, LocalTime.MIN, LocalTime.MAX, normOfCalories);
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public int getCalories() {
        return calories;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public boolean isExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id",          getId())
                .add("date_time",   getDateTime())
                .add("description", getDescription())
                .add("calories",    getCalories())
                .add("excess",      isExcess())
                .toString();
    }
}