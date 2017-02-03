package ru.tandser.solution.util;

import ru.tandser.solution.domain.AbstractEntity;
import ru.tandser.solution.service.exc.NotFoundException;
import ru.tandser.solution.web.exc.BadRequestException;

public class Inspector {

    private Inspector() {}

    public static void requireNotNull(Object... objs) {
        for (Object obj : objs) {
            if (obj == null) {
                throw new BadRequestException();
            }
        }
    }

    public static void requireNew(AbstractEntity entity) {
        if (entity == null || !entity.isNew()) {
            throw new BadRequestException();
        }
    }

    public static void requireConsistency(AbstractEntity entity, int id) {
        if (entity == null || (!entity.isNew() && entity.getId() != id)) {
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