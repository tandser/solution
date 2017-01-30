package ru.tandser.solution.web.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.dto.MealWithExcess;
import ru.tandser.solution.web.AbstractMealController;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(MealRestController.REST_PATH)
public class MealRestController extends AbstractMealController {

    public static final String REST_PATH = "/rest/meals";

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExcess> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/between", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExcess> getBetween(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to) {
        return super.getBetween(from, to);
    }

    @Override
    @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal getWithUser(@PathVariable int id) {
        return super.getWithUser(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void remove(@PathVariable int id) {
        super.remove(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> saveWithLocation(@RequestBody Meal meal) {
        Meal created = save(meal);

        URI newResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_PATH + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(newResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Meal meal, @PathVariable int id) {
        super.update(meal, id);
    }
}