package ru.tandser.solution.service;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/repository.xml", "classpath:spring/service.xml"})
@Sql(scripts = "classpath:ddl/insert.ddl", config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void beforeClass() throws Exception {
        UserTestData.loadMocks();
        MealTestData.loadMocks();
    }
}