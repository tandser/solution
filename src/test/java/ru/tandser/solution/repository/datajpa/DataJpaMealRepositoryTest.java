package ru.tandser.solution.repository.datajpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.tandser.solution.repository.AbstractRepositoryTest;
import ru.tandser.solution.repository.MealRepository;
import ru.tandser.solution.repository.exc.ConflictException;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static ru.tandser.solution.MealTestData.*;
import static ru.tandser.solution.UserTestData.nonExistentUser;
import static ru.tandser.solution.UserTestData.user;

public class DataJpaMealRepositoryTest extends AbstractRepositoryTest {

    private MealRepository mealRepository;

    @Autowired
    public void setMealRepository(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Test
    public void testGet() {
        assertNull(mealRepository.get(existingMeal.getId(), nonExistentUser.getId()));
        assertNull(mealRepository.get(nonExistentMeal.getId(), user.getId()));
        assertTrue(MEAL_MATCHER.equals(existingMeal, mealRepository.get(existingMeal.getId(), user.getId())));
    }

    @Test
    public void testGetAll() {
        assertNull(mealRepository.getAll(nonExistentUser.getId()));
        assertTrue(MEAL_MATCHER.equals(meals, mealRepository.getAll(user.getId())));
    }

    @Test
    public void testBetween() {
        assertNull(mealRepository.getBetween(from, to, nonExistentUser.getId()));
        assertTrue(MEAL_MATCHER.equals(betweenMeals, mealRepository.getBetween(from, to, user.getId())));
    }

    @Test
    public void testRemove() {
        assertNull(mealRepository.remove(existingMeal.getId(), nonExistentUser.getId()));
        assertNull(mealRepository.remove(nonExistentMeal.getId(), user.getId()));

        assertTrue(MEAL_MATCHER.equals(existingMeal, mealRepository.remove(existingMeal.getId(), user.getId())));
        assertTrue(MEAL_MATCHER.equals(updatedMeals, mealRepository.getAll(user.getId())));
    }

    @Test
    public void testPut() {
        assertNull(mealRepository.put(newMeal, nonExistentUser.getId()));
        assertNull(newMeal.getId());

        assertTrue(MEAL_MATCHER.equals(newMeal, mealRepository.put(newMeal, user.getId())));
        assertTrue(MEAL_MATCHER.equals(newMeal, mealRepository.get(newMeal.getId(), user.getId())));

        newMeal.setId(null);

        assertTrue(MEAL_MATCHER.equals(notNewMeal, mealRepository.put(notNewMeal, user.getId())));
        assertTrue(MEAL_MATCHER.equals(notNewMeal, mealRepository.get(notNewMeal.getId(), user.getId())));
    }

    @Test
    public void testPutConflictMeal() {
        thrown.expect(ConflictException.class);
        mealRepository.put(conflictMeal, user.getId());
    }

    @Test
    public void testPutDuplicateMeal() {
        thrown.expect(DataAccessException.class);
        mealRepository.put(duplicateMeal, user.getId());
    }

    @Test
    public void testValidation() {
        validateRootCause(() -> mealRepository.put(invalidDateTimeMeal,    user.getId()), ConstraintViolationException.class);
        validateRootCause(() -> mealRepository.put(invalidDescriptionMeal, user.getId()), ConstraintViolationException.class);
        validateRootCause(() -> mealRepository.put(invalidCaloriesMeal,    user.getId()), ConstraintViolationException.class);
    }
}