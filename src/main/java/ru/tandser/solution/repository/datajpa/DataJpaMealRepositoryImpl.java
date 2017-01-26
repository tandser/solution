package ru.tandser.solution.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.repository.MealRepository;

import java.time.LocalDateTime;
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
    public Meal get(int id, int userId) {
        return mealRepository.findOneByIdAndUserId(id, userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return mealRepository.findAllByUserId(userId);
    }

    @Override
    public Meal remove(int id, int userId) {
        List<Meal> result = mealRepository.removeByIdAndUserId(id, userId);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    @Transactional
    public Meal put(Meal meal, int userId) {
        if (!userRepository.exists(userId)) {
            return null;
        }

        if (!meal.isNew() && get(meal.getId(), userId) == null) {
            return null;
        }

        meal.setUser(userRepository.getOne(userId));

        return mealRepository.save(meal);
    }

    @Override
    public List<Meal> between(LocalDateTime from, LocalDateTime to, int userId) {
        return mealRepository.findByUserIdAndDateTimeBetween(userId, from, to);
    }
}