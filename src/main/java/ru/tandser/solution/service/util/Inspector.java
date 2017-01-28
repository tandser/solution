package ru.tandser.solution.service.util;

import ru.tandser.solution.domain.AbstractEntity;
import ru.tandser.solution.service.exc.NotFoundException;

public class Inspector {

    private Inspector() {}

    public static <T> T checkThatFound(T object, String message) {
        if (object == null) {
            throw new NotFoundException(message);
        }

        return object;
    }

    public static void checkThatNew(AbstractEntity entity, String message) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkThatNotNew(AbstractEntity entity, String message) {
        if (entity.isNew()) {
            throw new IllegalArgumentException(message);
        }
    }
}