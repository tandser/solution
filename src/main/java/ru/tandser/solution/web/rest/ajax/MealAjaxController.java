package ru.tandser.solution.web.rest.ajax;

import org.springframework.web.bind.annotation.*;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.dto.MealWithExcess;
import ru.tandser.solution.web.controller.AbstractMealController;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(MealAjaxController.AJAX_PATH)
public class MealAjaxController extends AbstractMealController {

    public static final String AJAX_PATH = "/ajax/meals";

    @Override
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<MealWithExcess> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/between", produces = APPLICATION_JSON_VALUE)
    public List<MealWithExcess> getBetween(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to) {
        return super.getBetween(from, to);
    }

    @Override
    @DeleteMapping("/{id}")
    public void remove(@PathVariable int id) {
        super.remove(id);
    }

    @PostMapping
    public void saveOrUpdate(Meal meal) {
        if (meal.isNew()) {
            save(meal);
        } else {
            update(meal, meal.getId());
        }
    }
}