package ru.tandser.solution.repository;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static ru.tandser.solution.MealTestData.*;

public class DataJpaMealRepositoryTest extends AbstractRepositoryTest {

    private MealRepository mealRepository;

    @Autowired
    public void setMealRepository(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        loadMocks();
    }

    @Test
    public void testGet() throws Exception {
        assertNull(mealRepository.get(0, 2));
        assertNull(mealRepository.get(2, 0));
        assertTrue(MEAL_MATCHER.equals(meals.get(0), mealRepository.get(1, 2)));
    }

    @Test
    public void testGetAll() throws Exception {
        assertTrue(mealRepository.getAll(0).isEmpty());
        assertTrue(MEAL_MATCHER.equals(meals, mealRepository.getAll(2)));
    }

    @Test
    public void testRemove() throws Exception {
        assertNull(mealRepository.remove(0, 2));
        assertNull(mealRepository.remove(2, 0));

        assertTrue(MEAL_MATCHER.equals(meals.get(0), mealRepository.remove(1, 2)));
        assertTrue(MEAL_MATCHER.equals(meals.subList(1, meals.size()), mealRepository.getAll(2)));
    }

    @Test
    public void testPut() throws Exception {
        assertNull(mealRepository.put(newMeal, 0));
        assertNull(newMeal.getId());

        assertTrue(MEAL_MATCHER.equals(newMeal, mealRepository.put(newMeal, 2)));
        assertTrue(MEAL_MATCHER.equals(newMeal, mealRepository.get(newMeal.getId(), 2)));

        newMeal.setCalories(500);

        assertTrue(MEAL_MATCHER.equals(newMeal, mealRepository.put(newMeal, 2)));
        assertTrue(MEAL_MATCHER.equals(newMeal, mealRepository.get(newMeal.getId(), 2)));
    }

    @Test(expected = DataAccessException.class)
    public void testPutDuplicateUserIdDateTime() throws Exception {
        mealRepository.put(duplicateMeal, 2);
    }

    @Test
    public void testBetween() throws Exception {
        assertTrue(mealRepository.getBetween(from, to, 0).isEmpty());
        assertTrue(MEAL_MATCHER.equals(meals.subList(0, 6), mealRepository.getBetween(from, to, 2)));
    }

    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> mealRepository.put(invalidDateTimeMeal,    2), ConstraintViolationException.class);
        validateRootCause(() -> mealRepository.put(invalidDescriptionMeal, 2), ConstraintViolationException.class);
        validateRootCause(() -> mealRepository.put(invalidCaloriesMeal,    2), ConstraintViolationException.class);
    }
}