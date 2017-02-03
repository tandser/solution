package ru.tandser.solution.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.dto.MealWithExcess;
import ru.tandser.solution.service.MealService;
import ru.tandser.solution.web.Principal;

import java.time.LocalDateTime;
import java.util.List;

import static ru.tandser.solution.util.DateTimeUtils.atEndOfDay;
import static ru.tandser.solution.util.DateTimeUtils.atStartOfDay;
import static ru.tandser.solution.util.Inspector.*;

public abstract class AbstractMealController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private MealService mealService;

    @Autowired
    public void setMealService(MealService mealService) {
        this.mealService = mealService;
    }

    public Meal get(int id) {
        Principal principal = Principal.get();
        log.info("{}: .get({})", principal.getUsername(), id);
        return mealService.get(id, principal.getId());
    }

    public List<MealWithExcess> getAll() {
        Principal principal = Principal.get();
        log.info("{}: .getAll()", principal.getUsername());
        return MealWithExcess.convert(mealService.getAll(principal.getId()), principal.getNormOfCalories());
    }

    public List<MealWithExcess> getBetween(LocalDateTime from, LocalDateTime to) {
        requireNotNull(from, to);
        Principal principal = Principal.get();
        log.info("{}: .getBetween({}, {})", principal.getUsername(), from, to);
        List<Meal> meals = mealService.getBetween(atStartOfDay(from), atEndOfDay(to), principal.getId());
        return MealWithExcess.filter(meals, from.toLocalTime(), to.toLocalTime(), principal.getNormOfCalories());
    }

    public void remove(int id) {
        Principal principal = Principal.get();
        log.info("{}: .remove({})", principal.getUsername(), id);
        mealService.remove(id, principal.getId());
    }

    public Meal save(Meal meal) {
        requireNew(meal);
        Principal principal = Principal.get();
        log.info("{}: .save({})", principal.getUsername(), meal);
        return mealService.save(meal, principal.getId());
    }

    public void update(Meal meal, int id) {
        requireConsistency(meal, id);
        Principal principal = Principal.get();
        log.info("{}: .update({}, {})", principal.getUsername(), meal, id);
        mealService.update(meal, principal.getId());
    }
}