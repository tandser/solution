package ru.tandser.solution.repository;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

public class DataJpaMealRepositoryTest extends AbstractRepositoryTest {

    private MealRepository mealRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    public void setMealRepository(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }
}