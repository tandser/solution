package ru.tandser.solution.dto;

import com.google.common.base.MoreObjects;
import org.springframework.util.Assert;
import ru.tandser.solution.domain.Meal;

import java.time.LocalDateTime;

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