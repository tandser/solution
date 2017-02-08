package ru.tandser.solution.web.rest.ajax;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;
import ru.tandser.solution.domain.User;
import ru.tandser.solution.web.controller.AbstractUserController;
import ru.tandser.solution.web.json.Views;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(UserAjaxController.AJAX_PATH)
public class UserAjaxController extends AbstractUserController {

    public static final String AJAX_PATH = "/ajax/users";

    @Override
    @JsonView(Views.Website.class)
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public User get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @JsonView(Views.Website.class)
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    public void remove(@PathVariable int id) {
        super.remove(id);
    }

    @PostMapping
    public void saveOrUpdate(User user) {
        if (user.isNew()) {
            save(user);
        } else {
            update(user, user.getId());
        }
    }

    @Override
    @PostMapping("/toggle/{id}")
    public void toggle(@PathVariable int id, @RequestParam boolean state) {
        super.toggle(id, state);
    }
}