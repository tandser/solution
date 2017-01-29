package ru.tandser.solution.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tandser.solution.service.exc.NotFoundException;

import static java.lang.String.format;
import static org.junit.Assert.assertTrue;
import static ru.tandser.solution.MealTestData.*;
import static ru.tandser.solution.UserTestData.USER_MATCHER;
import static ru.tandser.solution.UserTestData.user;

public class MealServiceTest extends AbstractServiceTest {

    private MealService mealService;

    @Autowired
    public void setMealService(MealService mealService) {
        this.mealService = mealService;
    }

    @Test
    public void testGet() {
        assertTrue(MEAL_MATCHER.equals(meals.get(0), mealService.get(1, 2)));
    }

    @Test
    public void testGetNonExistentUser() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_1, 2, 0));
        mealService.get(2, 0);
    }

    @Test
    public void testGetNonExistentMeal() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_1, 0, 2));
        mealService.get(0, 2);
    }

    @Test
    public void testGetAll() {
        assertTrue(MEAL_MATCHER.equals(meals, mealService.getAll(2)));
    }

    @Test
    public void testGetAllNonExistentUser() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_2, 0));
        mealService.getAll(0);
    }

    @Test
    public void testBetween() {
        assertTrue(MEAL_MATCHER.equals(meals.subList(0, 6), mealService.getBetween(from, to, 2)));
    }

    @Test
    public void testBetweenNullFrom() {
        thrown.expect(IllegalArgumentException.class);
        mealService.getBetween(null, to, 2);
    }

    @Test
    public void testBetweenNullTo() {
        thrown.expect(IllegalArgumentException.class);
        mealService.getBetween(from, null, 2);
    }

    @Test
    public void testBetweenNonExistentUser() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_2, 0));
        mealService.getBetween(from, to, 0);
    }

    @Test
    public void testGetWithUser() {
        assertTrue(USER_MATCHER.equals(user, mealService.getWithUser(1, 2).getUser()));
    }

    @Test
    public void testGetWithNonExistentUser() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_1, 2, 0));
        mealService.getWithUser(2, 0);
    }

    @Test
    public void testGetNonExistentMealWithUser() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_1, 0, 2));
        mealService.getWithUser(0, 2);
    }

    @Test
    public void testRemove() {
        mealService.remove(1, 2);
        assertTrue(MEAL_MATCHER.equals(meals.subList(1, meals.size()), mealService.getAll(2)));
    }

    @Test
    public void testRemoveNonExistentUser() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_1, 2, 0));
        mealService.remove(2, 0);
    }

    @Test
    public void testRemoveNonExistentMeal() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_1, 0, 2));
        mealService.remove(0, 2);
    }

    @Test
    public void testSave() {
        assertTrue(MEAL_MATCHER.equals(newMeal1, mealService.save(newMeal1, 2)));
        assertTrue(MEAL_MATCHER.equals(newMeal1, mealService.get(newMeal1.getId(), 2)));
    }

    @Test
    public void testSaveNull() {
        thrown.expect(IllegalArgumentException.class);
        mealService.save(null, 2);
    }

    @Test
    public void testSaveNotNewMeal() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_3, notNewMeal.getId()));
        mealService.save(notNewMeal, 2);
    }

    @Test
    public void testSaveNonExistentUser() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_2, 0));
        mealService.save(newMeal2, 0);
    }

    @Test
    public void testUpdate() {
        mealService.update(notNewMeal, 2);
        assertTrue(MEAL_MATCHER.equals(notNewMeal, mealService.get(notNewMeal.getId(), 2)));
    }

    @Test
    public void testUpdateNull() {
        thrown.expect(IllegalArgumentException.class);
        mealService.update(null, 2);
    }

    @Test
    public void testUpdateNewMeal() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(MealServiceImpl.MESSAGE_4);
        mealService.update(newMeal3, 2);
    }

    @Test
    public void testUpdateNonExistentUser() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_1, notNewMeal.getId(), 0));
        mealService.update(notNewMeal, 0);
    }

    @Test
    public void testUpdateNonExistentMeal() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(MealServiceImpl.MESSAGE_1, nonExistentMeal.getId(), 2));
        mealService.update(nonExistentMeal, 2);
    }
}