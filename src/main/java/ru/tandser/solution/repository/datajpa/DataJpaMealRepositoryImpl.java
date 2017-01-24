package ru.tandser.solution.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.repository.MealRepository;

import java.util.List;

@Service("mealService") // удалить после тестирования
@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    private JpaMealRepository mealRepository;

    @Autowired
    public void setMealRepository(JpaMealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public Meal get(int id, int userId) {
        return mealRepository.findOneByIdAndUserId(id, userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return mealRepository.findAllByUserId(userId);
    }
}