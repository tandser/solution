package ru.tandser.solution.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import ru.tandser.solution.service.exc.NotFoundException;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ru.tandser.solution.MealTestData.MEAL_MATCHER;
import static ru.tandser.solution.MealTestData.reverseOrderMeals;
import static ru.tandser.solution.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void testGet() {
        assertTrue(USER_MATCHER.equals(admin, userService.get(admin.getId())));
        assertTrue(USER_MATCHER.equals(user,  userService.get(user.getId())));
    }

    @Test
    public void testGetNonExistentUser() {
        thrown.expect(NotFoundException.class);
        userService.get(nonExistentUser.getId());
    }

    @Test
    public void testGetAll() {
        assertTrue(USER_MATCHER.equals(Arrays.asList(admin, user), userService.getAll()));
    }

    @Test
    public void testGetByEmail() {
        assertTrue(USER_MATCHER.equals(user, userService.getByEmail(user.getEmail())));
    }

    @Test
    public void testGetByNonExistentEmail() {
        thrown.expect(NotFoundException.class);
        userService.getByEmail(nonExistentUser.getEmail());
    }

    @Test
    public void testGetWithMeals() {
        assertTrue(MEAL_MATCHER.equals(reverseOrderMeals, userService.getWithMeals(user.getId()).getMeals()));
    }

    @Test
    public void testGetNonExistentUserWithMeals() {
        thrown.expect(NotFoundException.class);
        userService.getWithMeals(nonExistentUser.getId());
    }

    @Test
    public void testRemove() {
        userService.remove(user.getId());
        assertTrue(USER_MATCHER.equals(Collections.singletonList(admin), userService.getAll()));
    }

    @Test
    public void testRemoveNonExistentUser() {
        thrown.expect(NotFoundException.class);
        userService.remove(nonExistentUser.getId());
    }

    @Test
    public void testSave() {
        assertTrue(USER_MATCHER.equals(newUser, userService.save(newUser)));
        assertTrue(USER_MATCHER.equals(newUser, userService.get(newUser.getId())));
    }

    @Test
    public void testSaveDuplicatedUser() {
        thrown.expect(DataIntegrityViolationException.class);
        userService.save(duplicatedUser);
    }

    @Test
    public void testUpdate() {
        userService.update(updatedUser);
        assertTrue(USER_MATCHER.equals(updatedUser, userService.get(updatedUser.getId())));
    }

    @Test
    public void testUpdateNonExistentUser() {
        thrown.expect(NotFoundException.class);
        userService.update(nonExistentUser);
    }

    @Test
    public void testUpdateConflictedUser() {
        thrown.expect(ObjectOptimisticLockingFailureException.class);
        userService.update(conflictedUser);
    }

    @Test
    public void testToggle() {
        userService.toggle(admin.getId(), false);
        assertFalse(userService.get(admin.getId()).getEnabled());

        userService.toggle(admin.getId(), true);
        assertTrue(userService.get(admin.getId()).getEnabled());
    }

    @Test
    public void testToggleNonExistentUser() {
        thrown.expect(NotFoundException.class);
        userService.toggle(nonExistentUser.getId(), false);
    }
}