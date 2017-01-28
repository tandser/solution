package ru.tandser.solution.repository;

import ru.tandser.solution.domain.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {

    Meal get(int id, int userId);

    List<Meal> getAll(int userId);

    List<Meal> getBetween(LocalDateTime from, LocalDateTime to, int userId);

    Meal getWithUser(int id, int userId);

    Meal remove(int id, int userId);

    Meal put(Meal meal, int userId);
}