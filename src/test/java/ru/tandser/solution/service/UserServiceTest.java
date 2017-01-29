package ru.tandser.solution.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tandser.solution.service.exc.NotFoundException;

import java.util.Arrays;
import java.util.Collections;

import static java.lang.String.format;
import static org.junit.Assert.assertTrue;
import static ru.tandser.solution.MealTestData.MEAL_MATCHER;
import static ru.tandser.solution.MealTestData.mealsReverseOrder;
import static ru.tandser.solution.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void testGet() {
        assertTrue(USER_MATCHER.equals(admin, userService.get(1)));
        assertTrue(USER_MATCHER.equals(user,  userService.get(2)));
    }

    @Test
    public void testGetNonExistentUser() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(UserServiceImpl.MESSAGE_1, 0));
        userService.get(0);
    }

    @Test
    public void testGetAll() {
        assertTrue(USER_MATCHER.equals(Arrays.asList(admin, user), userService.getAll()));
    }

    @Test
    public void testGetByEmail() {
        assertTrue(USER_MATCHER.equals(user, userService.getByEmail("s.welch@gmail.com")));
    }

    @Test
    public void testGetByNullEmail() {
        thrown.expect(IllegalArgumentException.class);
        userService.getByEmail(null);
    }

    @Test
    public void testGetByNonExistentEmail() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(UserServiceImpl.MESSAGE_2, "r.bass@gmail.com"));
        userService.getByEmail("r.bass@gmail.com");
    }

    @Test
    public void testGetWithMeals() {
        assertTrue(MEAL_MATCHER.equals(mealsReverseOrder, userService.getWithMeals(2).getMeals()));
    }

    @Test
    public void testGetNonExistentUserWithMeals() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(UserServiceImpl.MESSAGE_1, 0));
        userService.getWithMeals(0);
    }

    @Test
    public void testRemove() {
        userService.remove(1);
        assertTrue(USER_MATCHER.equals(Collections.singletonList(user), userService.getAll()));
    }

    @Test
    public void testRemoveNonExistentUser() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(UserServiceImpl.MESSAGE_1, 0));
        userService.remove(0);
    }

    @Test
    public void testSave() {
        assertTrue(USER_MATCHER.equals(newUser, userService.save(newUser)));
        assertTrue(USER_MATCHER.equals(newUser, userService.get(newUser.getId())));
    }

    @Test
    public void testSaveNull() {
        thrown.expect(IllegalArgumentException.class);
        userService.save(null);
    }

    @Test
    public void testSaveNotNewUser() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(format(UserServiceImpl.MESSAGE_3, notNewUser.getId()));
        userService.save(notNewUser);
    }

    @Test
    public void testUpdate() {
        userService.update(notNewUser);
        assertTrue(USER_MATCHER.equals(notNewUser, userService.get(notNewUser.getId())));
    }

    @Test
    public void testUpdateNull() {
        thrown.expect(IllegalArgumentException.class);
        userService.update(null);
    }

    @Test
    public void testUpdateNewUser() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(UserServiceImpl.MESSAGE_4);
        userService.update(newUser);
    }

    @Test
    public void testUpdateNonExistentUser() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(format(UserServiceImpl.MESSAGE_1, nonExistentUser.getId()));
        userService.update(nonExistentUser);
    }
}