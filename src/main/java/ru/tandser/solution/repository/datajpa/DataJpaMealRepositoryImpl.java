package ru.tandser.solution.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.repository.MealRepository;

import java.util.List;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    private JpaMealRepository mealRepository;
    private JpaUserRepository userRepository;

    @Autowired
    public void setMealRepository(JpaMealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Autowired
    public void setUserRepository(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Meal get(Integer id, Integer userId) {
        return mealRepository.findOneByIdAndUserId(id, userId);
    }

    @Override
    public List<Meal> getAll(Integer userId) {
        return mealRepository.findAllByUserId(userId);
    }

    @Override
    public Meal remove(Integer id, Integer userId) {
        List<Meal> result = mealRepository.removeByIdAndUserId(id, userId);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    @Transactional
    public Meal put(Meal meal, Integer userId) {
        if (!userRepository.exists(userId)) {
            return null;
        }

        if (!meal.isNew() && get(meal.getId(), userId) == null) {
            return null;
        }

        meal.setUser(userRepository.getOne(userId));

        return mealRepository.save(meal);
    }
}