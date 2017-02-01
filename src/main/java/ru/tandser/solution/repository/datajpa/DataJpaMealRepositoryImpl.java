package ru.tandser.solution.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.repository.MealRepository;
import ru.tandser.solution.repository.exc.ConflictException;

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
    @Transactional(readOnly = true)
    public List<Meal> getAll(int userId) {
        return userRepository.exists(userId)
                ? mealRepository.findAllByUserId(userId)
                : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Meal> getBetween(LocalDateTime from, LocalDateTime to, int userId) {
        return userRepository.exists(userId)
                ? mealRepository.findByUserIdAndDateTimeBetween(userId, from, to)
                : null;
    }

    @Override
    public Meal getWithUser(int id, int userId) {
        return mealRepository.findOneByIdAndUserIdWithUser(id, userId);
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

        if (!meal.isNew()) {
            Meal last = get(meal.getId(), userId);
            if (last == null) {
                return null;
            } else if (meal.getVersion() != last.getVersion()) {
                throw new ConflictException();
            }
        }

        meal.setUser(userRepository.getOne(userId));

        return mealRepository.save(meal);
    }
}