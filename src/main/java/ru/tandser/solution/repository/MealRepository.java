package ru.tandser.solution.repository;

import ru.tandser.solution.domain.Meal;

import java.util.List;
import java.util.function.Predicate;

public interface MealRepository {
    void put(int userId, Meal meal);
    List<Meal> filter(int userId, Predicate<Meal> predicate);
}