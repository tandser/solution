package ru.tandser.solution.util;

import ru.tandser.solution.service.exceptions.NotFoundException;

public class Inspector {

    private Inspector() {}

    public static <T> T checkFound(T entity, int id) {
        if (entity == null) {
            throw new NotFoundException("Entity with id = " + id + " not found");
        }

        return entity;
    }
}