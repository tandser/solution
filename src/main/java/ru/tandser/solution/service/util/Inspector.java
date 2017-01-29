package ru.tandser.solution.service.util;

import ru.tandser.solution.domain.AbstractEntity;
import ru.tandser.solution.service.exc.NotFoundException;

public class Inspector {

    private Inspector() {}

    public static <T> T requireExist(T object, String message) {
        if (object == null) {
            throw new NotFoundException(message);
        }

        return object;
    }

    public static void requireNew(AbstractEntity entity, String message) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void requireNotNew(AbstractEntity entity, String message) {
        if (entity.isNew()) {
            throw new IllegalArgumentException(message);
        }
    }
}