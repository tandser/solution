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
import static ru.tandser.solution.MealTestData.mealsReverseOrder;
import static ru.tandser.solution.UserTestData.*;

public class DataJpaUserRepositoryTest extends AbstractRepositoryTest {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void testGet() {
        assertNull(userRepository.get(0));
        assertTrue(USER_MATCHER.equals(admin, userRepository.get(1)));
        assertTrue(USER_MATCHER.equals(user,  userRepository.get(2)));
    }

    @Test
    public void testGetAll() {
        assertTrue(USER_MATCHER.equals(Arrays.asList(admin, user), userRepository.getAll()));
    }

    @Test
    public void testGetByEmail() {
        assertNull(userRepository.getByEmail("r.bass@gmail.com"));
        assertTrue(USER_MATCHER.equals(user, userRepository.getByEmail("s.welch@gmail.com")));
    }

    @Test
    public void testGetWithMeals() {
        assertNull(userRepository.getWithMeals(0));
        assertTrue(MEAL_MATCHER.equals(mealsReverseOrder, userRepository.getWithMeals(2).getMeals()));
    }

    @Test
    public void testRemove() {
        assertNull(userRepository.remove(0));
        assertTrue(USER_MATCHER.equals(admin, userRepository.remove(1)));
        assertTrue(USER_MATCHER.equals(Collections.singletonList(user), userRepository.getAll()));
    }

    @Test
    public void testPut() {
        assertTrue(USER_MATCHER.equals(newUser, userRepository.put(newUser)));
        assertTrue(USER_MATCHER.equals(newUser, userRepository.get(newUser.getId())));

        newUser.setPassword("lgZtBlx");

        assertTrue(USER_MATCHER.equals(newUser, userRepository.put(newUser)));
        assertTrue(USER_MATCHER.equals(newUser, userRepository.get(newUser.getId())));
    }

    @Test(expected = DataAccessException.class)
    public void testPutDuplicateEmail() {
        userRepository.put(duplicateUser);
    }

    @Test
    public void testUpdate() {
        assertNull(userRepository.update(newUser));
        assertNull(userRepository.update(nonExistentUser));
        assertTrue(USER_MATCHER.equals(notNewUser, userRepository.update(notNewUser)));
        assertTrue(USER_MATCHER.equals(notNewUser, userRepository.get(notNewUser.getId())));
    }

    @Test
    public void testValidation() {
        validateRootCause(() -> userRepository.put(invalidNameUser),           ConstraintViolationException.class);
        validateRootCause(() -> userRepository.put(invalidEmailUser),          ConstraintViolationException.class);
        validateRootCause(() -> userRepository.put(invalidPasswordUser),       ConstraintViolationException.class);
        validateRootCause(() -> userRepository.put(invalidNormOfCaloriesUser), ConstraintViolationException.class);
    }
}