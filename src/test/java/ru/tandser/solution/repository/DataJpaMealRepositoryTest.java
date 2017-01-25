package ru.tandser.solution.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static ru.tandser.solution.MealTestData.*;

public class DataJpaMealRepositoryTest extends AbstractRepositoryTest {

    private MealRepository mealRepository;

    @Autowired
    public void setMealRepository(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Test
    public void testGet() throws Exception {
        assertNull(mealRepository.get(0, 2));
        assertNull(mealRepository.get(2, 0));
        assertTrue(MEAL_MATCHER.equals(meal, mealRepository.get(1, 2)));
    }

    @Test
    public void testGetAll() throws Exception {

    }
}