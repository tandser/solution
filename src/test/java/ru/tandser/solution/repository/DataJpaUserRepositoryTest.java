package ru.tandser.solution.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static ru.tandser.solution.MealTestData.MEAL_MATCHER;
import static ru.tandser.solution.MealTestData.reverseOrderMeals;
import static ru.tandser.solution.UserTestData.*;

public class DataJpaUserRepositoryTest extends AbstractRepositoryTest {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void testGet() {
        assertNull(userRepository.get(nonExistentUser.getId()));
        assertTrue(USER_MATCHER.equals(admin, userRepository.get(admin.getId())));
        assertTrue(USER_MATCHER.equals(user,  userRepository.get(user.getId())));
    }

    @Test
    public void testGetAll() {
        assertTrue(USER_MATCHER.equals(Arrays.asList(admin, user), userRepository.getAll()));
    }

    @Test
    public void testGetByEmail() {
        assertNull(userRepository.getByEmail(nonExistentUser.getEmail()));
        assertTrue(USER_MATCHER.equals(user, userRepository.getByEmail(user.getEmail())));
    }

    @Test
    public void testGetWithMeals() {
        assertNull(userRepository.getWithMeals(nonExistentUser.getId()));
        assertTrue(MEAL_MATCHER.equals(reverseOrderMeals, userRepository.getWithMeals(user.getId()).getMeals()));
    }

    @Test
    public void testRemove() {
        assertNull(userRepository.remove(nonExistentUser.getId()));
        assertTrue(USER_MATCHER.equals(user, userRepository.remove(user.getId())));
        assertTrue(USER_MATCHER.equals(Collections.singletonList(admin), userRepository.getAll()));
    }

    @Test
    public void testPut() {
        assertNull(userRepository.put(nonExistentUser));

        assertTrue(USER_MATCHER.equals(newUser, userRepository.put(newUser)));
        assertTrue(USER_MATCHER.equals(newUser, userRepository.get(newUser.getId())));

        newUser.setId(null);

        assertTrue(USER_MATCHER.equals(notNewUser, userRepository.put(notNewUser)));
        assertTrue(USER_MATCHER.equals(notNewUser, userRepository.get(notNewUser.getId())));
    }

    @Test
    public void testPutDuplicateUser() {
        thrown.expect(DataAccessException.class);
        userRepository.put(duplicateUser);
    }

    @Test
    public void testValidation() {
        validateRootCause(() -> userRepository.put(invalidNameUser),           ConstraintViolationException.class);
        validateRootCause(() -> userRepository.put(invalidEmailUser),          ConstraintViolationException.class);
        validateRootCause(() -> userRepository.put(invalidPasswordUser),       ConstraintViolationException.class);
        validateRootCause(() -> userRepository.put(invalidNormOfCaloriesUser), ConstraintViolationException.class);
    }
}