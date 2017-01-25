package ru.tandser.solution.repository;

import org.hamcrest.CoreMatchers;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/datajpa-test.xml")
@Sql(scripts = "classpath:scripts/insert.ddl", config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractRepositoryTest {

    public static <T extends Throwable> void validateRootCause(Runnable task, Class<T> exceptionType) {
        try {
            task.run();
            fail("Expected " + exceptionType.getName());
        } catch (Exception exc) {
            assertThat(getRootCause(exc), CoreMatchers.instanceOf(exceptionType));
        }
    }

    private static Throwable getRootCause(Throwable exc) {
        Throwable result = exc, cause;

        while ((cause = result.getCause()) != null && (result != cause)) {
            result = cause;
        }

        return result;
    }
}