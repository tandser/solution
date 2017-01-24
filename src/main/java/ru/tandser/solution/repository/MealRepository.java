package ru.tandser.solution.repository;

import ru.tandser.solution.domain.Meal;

import java.util.List;

public interface MealRepository {

    Meal get(Integer id, Integer userId);

    List<Meal> getAll(Integer userId);

    Meal remove(Integer id, Integer userId);

    Meal put(Meal meal, Integer userId);
}