package ru.tandser.solution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

import static java.lang.String.format;
import static ru.tandser.solution.service.util.Inspector.*;

@Service
public class MealServiceImpl implements MealService {

    public static final String MESSAGE_1 = "Meal with id = %d for user with id = %d does not exist";
    public static final String MESSAGE_2 = "User with id = %d does not exist";
    public static final String MESSAGE_3 = "Meal id = %d, must be null";
    public static final String MESSAGE_4 = "Meal id must not be null";

    private MealRepository mealRepository;

    @Autowired
    public void setMealRepository(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public Meal get(int id, int userId) {
        return requireExist(mealRepository.get(id, userId), format(MESSAGE_1, id, userId));
    }

    @Override
    public List<Meal> getAll(int userId) {
        return requireExist(mealRepository.getAll(userId), format(MESSAGE_2, userId));
    }

    @Override
    public List<Meal> getBetween(LocalDateTime from, LocalDateTime to, int userId) {
        Assert.notNull(from);
        Assert.notNull(to);
        return requireExist(mealRepository.getBetween(from, to, userId), format(MESSAGE_2, userId));
    }

    @Override
    public Meal getWithUser(int id, int userId) {
        return requireExist(mealRepository.getWithUser(id, userId), format(MESSAGE_1, id, userId));
    }

    @Override
    public void remove(int id, int userId) {
        requireExist(mealRepository.remove(id, userId), format(MESSAGE_1, id, userId));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        Assert.notNull(meal);
        requireNew(meal, format(MESSAGE_3, meal.getId()));
        return requireExist(mealRepository.put(meal, userId), format(MESSAGE_2, userId));
    }

    @Override
    public void update(Meal meal, int userId) {
        Assert.notNull(meal);
        requireNotNew(meal, MESSAGE_4);
        requireExist(mealRepository.put(meal, userId), format(MESSAGE_1, meal.getId(), userId));
    }
}