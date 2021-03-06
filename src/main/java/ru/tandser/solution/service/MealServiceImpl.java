package ru.tandser.solution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.tandser.solution.util.Inspector.requireExist;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository mealRepository;

    @Autowired
    public void setMealRepository(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public Meal get(int id, int userId) {
        return requireExist(mealRepository.get(id, userId));
    }

    @Override
    public List<Meal> getAll(int userId) {
        return requireExist(mealRepository.getAll(userId));
    }

    @Override
    public List<Meal> getBetween(LocalDateTime from, LocalDateTime to, int userId) {
        return requireExist(mealRepository.getBetween(from, to, userId));
    }

    @Override
    public void remove(int id, int userId) {
        requireExist(mealRepository.remove(id, userId));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        return requireExist(mealRepository.put(meal, userId));
    }

    @Override
    public void update(Meal meal, int userId) {
        requireExist(mealRepository.put(meal, userId));
    }
}