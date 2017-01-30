package ru.tandser.solution.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tandser.solution.service.exc.NotFoundException;

import static java.lang.String.format;
import static org.junit.Assert.assertTrue;
import static ru.tandser.solution.MealTestData.*;
import static ru.tandser.solution.UserTestData.*;

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
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_1, existingMeal.getId(), nonExistentUser.getId()));
        mealService.get(existingMeal.getId(), nonExistentUser.getId());
    }

    @Test
    public void testGetNonExistentMeal() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_1, nonExistentMeal.getId(), user.getId()));
        mealService.get(nonExistentMeal.getId(), user.getId());
    }

    @Test
    public void testGetAll() {
        assertTrue(MEAL_MATCHER.equals(meals, mealService.getAll(user.getId())));
    }

    @Test
    public void testGetAllForNonExistentUser() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_2, nonExistentUser.getId()));
        mealService.getAll(nonExistentUser.getId());
    }

    @Test
    public void testBetween() {
        assertTrue(MEAL_MATCHER.equals(betweenMeals, mealService.getBetween(from, to, user.getId())));
    }

    @Test
    public void testBetweenNullFrom() {
        thrown.expect(IllegalArgumentException.class);
        mealService.getBetween(null, to, user.getId());
    }

    @Test
    public void testBetweenNullTo() {
        thrown.expect(IllegalArgumentException.class);
        mealService.getBetween(from, null, user.getId());
    }

    @Test
    public void testBetweenForNonExistentUser() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_2, nonExistentUser.getId()));
        mealService.getBetween(from, to, nonExistentUser.getId());
    }

    @Test
    public void testGetWithUser() {
        assertTrue(USER_MATCHER.equals(user, mealService.getWithUser(existingMeal.getId(), user.getId()).getUser()));
    }

    @Test
    public void testGetWithNonExistentUser() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_1, existingMeal.getId(), nonExistentUser.getId()));
        mealService.getWithUser(existingMeal.getId(), nonExistentUser.getId());
    }

    @Test
    public void testGetNonExistentMealWithUser() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_1, nonExistentMeal.getId(), user.getId()));
        mealService.getWithUser(nonExistentMeal.getId(), user.getId());
    }

    @Test
    public void testRemove() {
        mealService.remove(existingMeal.getId(), user.getId());
        assertTrue(MEAL_MATCHER.equals(updatedMeals, mealService.getAll(user.getId())));
    }

    @Test
    public void testRemoveForNonExistentUser() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_1, existingMeal.getId(), nonExistentUser.getId()));
        mealService.remove(existingMeal.getId(), nonExistentUser.getId());
    }

    @Test
    public void testRemoveNonExistentMeal() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_1, nonExistentMeal.getId(), user.getId()));
        mealService.remove(nonExistentMeal.getId(), user.getId());
    }

    @Test
    public void testSave() {
        assertTrue(MEAL_MATCHER.equals(newMeal, mealService.save(newMeal, user.getId())));
        assertTrue(MEAL_MATCHER.equals(newMeal, mealService.get(newMeal.getId(), user.getId())));
        newMeal.setId(null);
    }

    @Test
    public void testSaveNull() {
        thrown.expect(IllegalArgumentException.class);
        mealService.save(null, user.getId());
    }

    @Test
    public void testSaveNotNewMeal() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_3, notNewMeal.getId()));
        mealService.save(notNewMeal, user.getId());
    }

    @Test
    public void testSaveForNonExistentUser() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_2, nonExistentUser.getId()));
        mealService.save(newMeal, nonExistentUser.getId());
    }

    @Test
    public void testUpdate() {
        mealService.update(notNewMeal, user.getId());
        assertTrue(MEAL_MATCHER.equals(notNewMeal, mealService.get(notNewMeal.getId(), user.getId())));
    }

    @Test
    public void testUpdateNull() {
        thrown.expect(IllegalArgumentException.class);
        mealService.update(null, user.getId());
    }

    @Test
    public void testUpdateNewMeal() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(MealServiceImpl.MESSAGE_4);
        mealService.update(newMeal, user.getId());
    }

    @Test
    public void testUpdateForNonExistentUser() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_1, notNewMeal.getId(), nonExistentUser.getId()));
        mealService.update(notNewMeal, nonExistentUser.getId());
    }

    @Test
    public void testUpdateNonExistentMeal() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_1, nonExistentMeal.getId(), user.getId()));
        mealService.update(nonExistentMeal, user.getId());
    }
}