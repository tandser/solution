package ru.tandser.solution.repository;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.tandser.solution.MealTestData;
import ru.tandser.solution.UserTestData;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/repository.xml")
@Sql(scripts = "classpath:ddl/insert.ddl", config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractRepositoryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void beforeClass() throws Exception {
        UserTestData.loadMocks();
        MealTestData.loadMocks();
    }

    public static <T extends Throwable> void validateRootCause(Runnable task, Class<T> exceptionType) {
        try {
            task.run();
            fail("Expected " + exceptionType.getName());
        } catch (Exception exc) {
            assertThat(getRootCause(exc), CoreMatchers.instanceOf(exceptionType));
        }
    }

    private static Throwable getRootCause(Throwable exc) {
        Throwable cause, result = exc;

        while ((cause = result.getCause()) != null && (result != cause)) {
            result = cause;
        }

        return result;
    }
}