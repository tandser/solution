package ru.tandser.solution.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.tandser.solution.domain.User;
import ru.tandser.solution.web.controller.AbstractUserController;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(UserRestController.REST_PATH)
public class UserRestController extends AbstractUserController {

    public static final String REST_PATH = "/rest/users";

    @Override
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public User get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/by", produces = APPLICATION_JSON_VALUE)
    public User getByEmail(@RequestParam String email) {
        return super.getByEmail(email);
    }

    @Override
    @GetMapping(value = "/details/{id}", produces = APPLICATION_JSON_VALUE)
    public User getWithMeals(@PathVariable int id) {
        return super.getWithMeals(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void remove(@PathVariable int id) {
        super.remove(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveWithLocation(@RequestBody User user) {
        User created = save(user);

        URI newResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_PATH + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(newResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user, @PathVariable int id) {
        super.update(user, id);
    }
}