package ru.tandser.solution.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import ru.tandser.solution.service.exc.NotFoundException;

import static org.junit.Assert.assertTrue;
import static ru.tandser.solution.MealTestData.*;
import static ru.tandser.solution.UserTestData.nonExistentUser;
import static ru.tandser.solution.UserTestData.user;

public class MealServiceTest extends AbstractServiceTest {

    private MealService mealService;

    @Autowired
    public void setMealService(MealService mealService) {
        this.mealService = mealService;
    }

    @Test
    public void testGet() {
        assertTrue(MEAL_MATCHER.equals(existingMeal, mealService.get(existingMeal.getId(), user.getId())));
    }

    @Test
    public void testGetForNonExistentUser() {
        thrown.expect(NotFoundException.class);
        mealService.get(existingMeal.getId(), nonExistentUser.getId());
    }

    @Test
    public void testGetNonExistentMeal() {
        thrown.expect(NotFoundException.class);
        mealService.get(nonExistentMeal.getId(), user.getId());
    }

    @Test
    public void testGetAll() {
        assertTrue(MEAL_MATCHER.equals(meals, mealService.getAll(user.getId())));
    }

    @Test
    public void testGetAllForNonExistentUser() {
        thrown.expect(NotFoundException.class);
        mealService.getAll(nonExistentUser.getId());
    }

    @Test
    public void testBetween() {
        assertTrue(MEAL_MATCHER.equals(betweenMeals, mealService.getBetween(from, to, user.getId())));
    }

    @Test
    public void testBetweenForNonExistentUser() {
        thrown.expect(NotFoundException.class);
        mealService.getBetween(from, to, nonExistentUser.getId());
    }

    @Test
    public void testRemove() {
        mealService.remove(existingMeal.getId(), user.getId());
        assertTrue(MEAL_MATCHER.equals(updatedMeals, mealService.getAll(user.getId())));
    }

    @Test
    public void testRemoveForNonExistentUser() {
        thrown.expect(NotFoundException.class);
        mealService.remove(existingMeal.getId(), nonExistentUser.getId());
    }

    @Test
    public void testRemoveNonExistentMeal() {
        thrown.expect(NotFoundException.class);
        mealService.remove(nonExistentMeal.getId(), user.getId());
    }

    @Test
    public void testSave() {
        assertTrue(MEAL_MATCHER.equals(newMeal, mealService.save(newMeal, user.getId())));
        assertTrue(MEAL_MATCHER.equals(newMeal, mealService.get(newMeal.getId(), user.getId())));
        newMeal.setId(null);
    }

    @Test
    public void testSaveForNonExistentUser() {
        thrown.expect(NotFoundException.class);
        mealService.save(newMeal, nonExistentUser.getId());
    }

    @Test
    public void testSaveDuplicatedMeal() {
        thrown.expect(DataIntegrityViolationException.class);
        mealService.save(duplicatedMeal, user.getId());
    }

    @Test
    public void testUpdate() {
        mealService.update(updatedMeal, user.getId());
        assertTrue(MEAL_MATCHER.equals(updatedMeal, mealService.get(updatedMeal.getId(), user.getId())));
    }

    @Test
    public void testUpdateForNonExistentUser() {
        thrown.expect(NotFoundException.class);
        mealService.update(updatedMeal, nonExistentUser.getId());
    }

    @Test
    public void testUpdateNonExistentMeal() {
        thrown.expect(NotFoundException.class);
        mealService.update(nonExistentMeal, user.getId());
    }

    @Test
    public void testUpdateConflictedMeal() {
        thrown.expect(ObjectOptimisticLockingFailureException.class);
        mealService.update(conflictedMeal, user.getId());
    }
}