package ru.tandser.solution.dto;

import ru.tandser.solution.domain.Meal;

import java.io.Serializable;
import java.util.List;

public class Meals implements Serializable {

    private List<Meal> meals;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}