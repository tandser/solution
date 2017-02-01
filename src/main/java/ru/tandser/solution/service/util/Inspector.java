package ru.tandser.solution.service.util;

import ru.tandser.solution.domain.AbstractEntity;
import ru.tandser.solution.service.exc.BadRequestException;
import ru.tandser.solution.service.exc.NotFoundException;

public class Inspector {

    private Inspector() {}

    public static void requireNotNull(Object obj) {
        if (obj == null) {
            throw new BadRequestException();
        }
    }

    public static void requireNew(AbstractEntity entity) {
        if (!entity.isNew()) {
            throw new BadRequestException();
        }
    }

    public static void requireNotNew(AbstractEntity entity) {
        if (entity.isNew()) {
            throw new BadRequestException();
        }
    }

    public static void requireConsistency(AbstractEntity entity, int id) {
        if (!entity.isNew() && entity.getId() != id) {
            throw new BadRequestException();
        }

        entity.setId(id);
    }

    public static <T> T requireExist(T result) {
        if (result == null) {
            throw new NotFoundException();
        }

        return result;
    }
}