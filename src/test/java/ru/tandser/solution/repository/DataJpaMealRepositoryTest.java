package ru.tandser.solution.repository;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/datajpa-test.xml")
@Sql(scripts = "classpath:scripts/insert.ddl", config = @SqlConfig(encoding = "UTF-8"))
public class DataJpaMealRepositoryTest {

    private MealRepository mealRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    public void setMealRepository(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }
}