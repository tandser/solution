package ru.tandser.solution.repository;

import ru.tandser.solution.domain.Meal;

import java.util.List;

public interface MealRepository {
    Meal get(int id, int userId);
    List<Meal> getAll(int userId);
}