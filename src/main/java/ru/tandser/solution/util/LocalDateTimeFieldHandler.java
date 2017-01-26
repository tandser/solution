package ru.tandser.solution.util;

import org.exolab.castor.mapping.ConfigurableFieldHandler;
import org.exolab.castor.mapping.ValidityException;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.domain.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Properties;

public class LocalDateTimeFieldHandler implements ConfigurableFieldHandler {

    private DateTimeFormatter formatter;

    @Override
    public void setConfiguration(Properties config) throws ValidityException {
        String pattern = config.getProperty("date-time-format");

        if (pattern == null) {
            throw new ValidityException("Required parameter \"date-time-format\" is missing for LocalDateTimeFieldHandler");
        }

        try {
            formatter = DateTimeFormatter.ofPattern(pattern);
        } catch (IllegalArgumentException exc) {
            throw new ValidityException("Pattern \"" + pattern + "\" is not a valid date-time format");
        }
    }

    @Override
    public Object getValue(Object object) throws IllegalStateException {
        String result = null;

        if (object instanceof User) {
            User user = (User) object;
            LocalDateTime dateTime = user.getCreated();
            if (dateTime == null) return null;
            result = dateTime.format(formatter);
        } else

        if (object instanceof Meal) {
            Meal meal = (Meal) object;
            LocalDateTime dateTime = meal.getDateTime();
            if (dateTime == null) return null;
            result = dateTime.format(formatter);
        }

        return result;
    }

    @Override
    public void setValue(Object object, Object value) throws IllegalStateException, IllegalArgumentException {
        LocalDateTime dateTime;

        try {
            dateTime = LocalDateTime.parse((String) value, formatter);
        } catch (DateTimeParseException exc) {
            dateTime = null;
        }

        if (object instanceof User) {
            User user = (User) object;
            user.setCreated(dateTime);
        } else

        if (object instanceof Meal) {
            Meal meal = (Meal) object;
            meal.setDateTime(dateTime);
        }
    }

    @Override
    public void resetValue(Object object) throws IllegalStateException, IllegalArgumentException {
        if (object instanceof User) {
            User user = (User) object;
            user.setCreated(null);
        } else

        if (object instanceof Meal) {
            Meal meal = (Meal) object;
            meal.setDateTime(null);
        }
    }

    @Override
    @SuppressWarnings("deprication")
    public void checkValidity(Object object) throws ValidityException, IllegalStateException {

    }

    @Override
    public Object newInstance(Object parent) throws IllegalStateException {
        return null;
    }
}