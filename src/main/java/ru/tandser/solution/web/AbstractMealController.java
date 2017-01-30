package ru.tandser.solution.web;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.dto.MealWithExcess;
import ru.tandser.solution.dto.util.MealUtils;
import ru.tandser.solution.service.MealService;

import java.time.LocalDateTime;
import java.util.List;

import static ru.tandser.solution.dto.util.DateTimeUtils.withMaxTime;
import static ru.tandser.solution.dto.util.DateTimeUtils.withMinTime;

public abstract class AbstractMealController {

    private MealService mealService;

    @Autowired
    public void setMealService(MealService mealService) {
        this.mealService = mealService;
    }

    // TODO: доработать

    public Meal get(int id) {
        return mealService.get(id, 2);
    }

    public List<MealWithExcess> getAll() {
        return MealUtils.toMealWithExcess(mealService.getAll(2), 2000);
    }

    public List<MealWithExcess> getBetween(LocalDateTime from, LocalDateTime to) {
        List<Meal> meals = mealService.getBetween(withMinTime(from), withMaxTime(to), 2);
        return MealUtils.toMealWithExcess(meals, from.toLocalTime(), to.toLocalTime(), 2000);
    }

    public void remove(int id) {
        mealService.remove(id, 2);
    }

    public Meal save(Meal meal) {
        meal.setId(null);
        return mealService.save(meal, 2);
    }

    public void update(Meal meal, int id) {
        meal.setId(id);
        mealService.update(meal, 2);
    }
}