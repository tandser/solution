package ru.tandser.solution.web;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.dto.MealWithExcess;
import ru.tandser.solution.service.MealService;

import java.time.LocalDateTime;
import java.util.List;

import static ru.tandser.solution.dto.util.DateTimeUtils.atEndOfDay;
import static ru.tandser.solution.dto.util.DateTimeUtils.atStartOfDay;

// TODO: привести в порядок

public abstract class AbstractMealController {

    private MealService mealService;

    @Autowired
    public void setMealService(MealService mealService) {
        this.mealService = mealService;
    }

    public Meal get(int id) {
        return mealService.get(id, 2);
    }

    public List<MealWithExcess> getAll() {
        return MealWithExcess.convert(mealService.getAll(2), 2000);
    }

    public List<MealWithExcess> getBetween(LocalDateTime from, LocalDateTime to) {
        List<Meal> meals = mealService.getBetween(atStartOfDay(from), atEndOfDay(to), 2);
        return MealWithExcess.filter(meals, from.toLocalTime(), to.toLocalTime(), 2000);
    }

    public Meal getWithUser(int id) {
        return mealService.getWithUser(id, 2);
    }

    public void remove(int id) {
        mealService.remove(id, 2);
    }

    public Meal save(Meal meal) {
        return mealService.save(meal, 2);
    }

    public void update(Meal meal, int id) {
        mealService.update(meal, 2);
    }
}