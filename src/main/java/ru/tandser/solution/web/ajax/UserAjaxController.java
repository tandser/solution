package ru.tandser.solution.web.ajax;

import org.springframework.web.bind.annotation.*;
import ru.tandser.solution.domain.User;
import ru.tandser.solution.web.controller.AbstractUserController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(UserAjaxController.AJAX_PATH)
public class UserAjaxController extends AbstractUserController {

    public static final String AJAX_PATH = "ajax/users";

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
    @DeleteMapping("/{id}")
    public void remove(@PathVariable int id) {
        super.remove(id);
    }



}