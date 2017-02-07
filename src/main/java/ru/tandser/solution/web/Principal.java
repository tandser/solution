package ru.tandser.solution.web;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.tandser.solution.domain.User;

import java.util.Collections;

public class Principal extends org.springframework.security.core.userdetails.User {

    private int id;
    private int normOfCalories;

    public Principal(User user) {
        super(user.getEmail(), user.getPassword(), user.getEnabled(), true, true, true,
                Collections.singletonList(user.getRole()));

        id = user.getId();
        normOfCalories = user.getNormOfCalories();
    }

    public static Principal get() {
        return (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public int getId() {
        return id;
    }

    public int getNormOfCalories() {
        return normOfCalories;
    }
}