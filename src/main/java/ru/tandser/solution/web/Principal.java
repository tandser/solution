package ru.tandser.solution.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.tandser.solution.domain.User;

import java.util.Collections;

public class Principal extends org.springframework.security.core.userdetails.User {

    public Principal(User user) {
        super(user.getEmail(), user.getPassword(), user.getEnabled(), true, true, true,
                Collections.singletonList(user.getRole()));
    }

    public static Principal get() {
        Authentication ation = SecurityContextHolder.getContext().getAuthentication();
        return ation != null ? (Principal) ation.getPrincipal() : null;
    }

    public static int id() {
        return 0;
    }
}