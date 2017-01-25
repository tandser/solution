package ru.tandser.solution.repository;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;
import static ru.tandser.solution.UserTestData.USER;
import static ru.tandser.solution.UserTestData.USER_MATCHER;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/datajpa-test.xml")
@Sql(scripts = "classpath:scripts/insert.ddl", config = @SqlConfig(encoding = "UTF-8"))
public class DataJpaUserRepositoryTest {

    private UserRepository userRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void testGet() {
        assertTrue(USER_MATCHER.equals(USER, userRepository.get(2)));
    }

    @Test
    public void testGetAll() {

    }
}