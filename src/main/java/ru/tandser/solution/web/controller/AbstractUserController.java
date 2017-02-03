package ru.tandser.solution.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tandser.solution.domain.User;
import ru.tandser.solution.service.UserService;
import ru.tandser.solution.service.util.Inspector;
import ru.tandser.solution.web.Principal;

import java.util.List;

import static ru.tandser.solution.service.util.Inspector.requireConsistency;

public abstract class AbstractUserController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User get(int id) {
        Principal principal = Principal.get();
        log.info("{}: .get({})", principal.getUsername(), id);
        return userService.get(id);
    }

    public List<User> getAll() {
        Principal principal = Principal.get();
        log.info("{}: .getAll()", principal.getUsername());
        return userService.getAll();
    }

    public User getByEmail(String email) {
        Principal principal = Principal.get();
        log.info("{}: .getByEmail({})", principal.getUsername(), email);
        return userService.getByEmail(email);
    }

    public User getWithMeals(int id) {
        Principal principal = Principal.get();
        log.info("{}: .getWithMeals({})", principal.getUsername(), id);
        return userService.getWithMeals(id);
    }

    public void remove(int id) {
        Principal principal = Principal.get();
        log.info("{}: .remove({})", principal.getUsername(), id);
        userService.remove(id);
    }

    public User save(User user) {
        Principal principal = Principal.get();
        log.info("{}: .save({})", principal.getUsername(), user);
        return userService.save(user);
    }

    public void update(User user, int id) {
        Principal principal = Principal.get();
        log.info("{}: .update({})", principal.getUsername(), user);
        requireConsistency(user, id);
        userService.update(user);
    }
}