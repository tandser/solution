package ru.tandser.solution.domain;

import com.google.common.base.MoreObjects;

import java.time.LocalDateTime;

public class Meal {
    private Integer id;
    private LocalDateTime dateTime;
    private String description;
    private Integer calories;

    public Meal(Integer id, LocalDateTime dateTime, String description, Integer calories) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Meal(LocalDateTime dateTime, String description, Integer calories) {
        this(null, dateTime, description, calories);
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCalories() {
        return calories;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("dateTime", dateTime)
                .add("description", description)
                .add("calories", calories)
                .toString();
    }
}