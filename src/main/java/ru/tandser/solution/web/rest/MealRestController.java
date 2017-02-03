package ru.tandser.solution.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.dto.MealWithExcess;
import ru.tandser.solution.web.controller.AbstractMealController;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(MealRestController.REST_PATH)
public class MealRestController extends AbstractMealController {

    public static final String REST_PATH = "/rest/meals";

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

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> saveWithLocation(@RequestBody Meal meal) {
        Meal created = save(meal);

        URI newResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_PATH + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(newResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody Meal meal, @PathVariable int id) {
        super.update(meal, id);
    }
}