package ru.tandser.solution.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static ru.tandser.solution.MealTestData.*;
import static ru.tandser.solution.UserTestData.USER_MATCHER;
import static ru.tandser.solution.UserTestData.user;

public class DataJpaMealRepositoryTest extends AbstractRepositoryTest {

    private MealRepository mealRepository;

    @Autowired
    public void setMealRepository(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Test
    public void testGet() {
        assertNull(mealRepository.get(0, 2));
        assertNull(mealRepository.get(2, 0));
        assertTrue(MEAL_MATCHER.equals(meals.get(0), mealRepository.get(1, 2)));
    }

    @Test
    public void testGetAll() {
        assertNull(mealRepository.getAll(0));
        assertTrue(MEAL_MATCHER.equals(meals, mealRepository.getAll(2)));
    }

    @Test
    public void testBetween() {
        assertNull(mealRepository.getBetween(from, to, 0));
        assertTrue(MEAL_MATCHER.equals(meals.subList(0, 6), mealRepository.getBetween(from, to, 2)));
    }

    @Test
    public void testGetWithUser() {
        assertNull(mealRepository.getWithUser(0, 2));
        assertNull(mealRepository.getWithUser(2, 0));
        assertTrue(USER_MATCHER.equals(user, mealRepository.getWithUser(1, 2).getUser()));
    }

    @Test
    public void testRemove() {
        assertNull(mealRepository.remove(0, 2));
        assertNull(mealRepository.remove(2, 0));

        assertTrue(MEAL_MATCHER.equals(meals.get(0), mealRepository.remove(1, 2)));
        assertTrue(MEAL_MATCHER.equals(meals.subList(1, meals.size()), mealRepository.getAll(2)));
    }

    @Test
    public void testPut() {
        assertNull(mealRepository.put(newMeal1, 0));
        assertNull(newMeal1.getId());

        assertTrue(MEAL_MATCHER.equals(newMeal1, mealRepository.put(newMeal1, 2)));
        assertTrue(MEAL_MATCHER.equals(newMeal1, mealRepository.get(newMeal1.getId(), 2)));

        newMeal1.setCalories(500);

        assertTrue(MEAL_MATCHER.equals(newMeal1, mealRepository.put(newMeal1, 2)));
        assertTrue(MEAL_MATCHER.equals(newMeal1, mealRepository.get(newMeal1.getId(), 2)));
    }

    @Test(expected = DataAccessException.class)
    public void testPutDuplicateUserIdDateTime() {
        mealRepository.put(duplicateMeal, 2);
    }

    @Test
    public void testValidation() {
        validateRootCause(() -> mealRepository.put(invalidDateTimeMeal,    2), ConstraintViolationException.class);
        validateRootCause(() -> mealRepository.put(invalidDescriptionMeal, 2), ConstraintViolationException.class);
        validateRootCause(() -> mealRepository.put(invalidCaloriesMeal,    2), ConstraintViolationException.class);
    }
}