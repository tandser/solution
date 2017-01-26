package ru.tandser.solution.repository;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.tandser.solution.UserTestData;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static ru.tandser.solution.UserTestData.*;

public class DataJpaUserRepositoryTest extends AbstractRepositoryTest {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        UserTestData.loadMocks();
    }

    @Test
    public void testGet() throws Exception {
        assertNull(userRepository.get(0));
        assertTrue(USER_MATCHER.equals(admin, userRepository.get(1)));
        assertTrue(USER_MATCHER.equals(user,  userRepository.get(2)));
    }

    @Test
    public void testGetAll() throws Exception {
        assertTrue(USER_MATCHER.equals(Arrays.asList(admin, user), userRepository.getAll()));
    }

    @Test
    public void testRemove() throws Exception {
        assertNull(userRepository.remove(0));
        assertTrue(USER_MATCHER.equals(admin, userRepository.remove(1)));
        assertTrue(USER_MATCHER.equals(Collections.singletonList(user), userRepository.getAll()));
    }

    @Test
    public void testPut() throws Exception {
        assertTrue(USER_MATCHER.equals(newUser, userRepository.put(newUser)));
        assertTrue(USER_MATCHER.equals(newUser, userRepository.get(newUser.getId())));

        newUser.setPassword("lgZtBlx");

        assertTrue(USER_MATCHER.equals(newUser, userRepository.put(newUser)));
        assertTrue(USER_MATCHER.equals(newUser, userRepository.get(newUser.getId())));
    }

    @Test(expected = DataAccessException.class)
    public void testPutDuplicateEmail() throws Exception {
        userRepository.put(duplicateUser);
    }

    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> userRepository.put(invalidNameUser),           ConstraintViolationException.class);
        validateRootCause(() -> userRepository.put(invalidEmailUser),          ConstraintViolationException.class);
        validateRootCause(() -> userRepository.put(invalidPasswordUser),       ConstraintViolationException.class);
        validateRootCause(() -> userRepository.put(invalidNormOfCaloriesUser), ConstraintViolationException.class);
    }
}