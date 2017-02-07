package ru.tandser.solution.web.rest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.tandser.solution.UserTestData;
import ru.tandser.solution.dto.MealWithExcess;
import ru.tandser.solution.service.MealService;
import ru.tandser.solution.web.AbstractControllerTest;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.tandser.solution.MealTestData.*;
import static ru.tandser.solution.UserTestData.user;

public class MealRestControllerTest extends AbstractControllerTest {

    private static final String REST_PATH = MealRestController.REST_PATH + '/';

    private MealService mealService;

    @Autowired
    public void setMealService(MealService mealService) {
        this.mealService = mealService;
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_PATH + existingMeal.getId()).with(userAccount))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(MEAL_MATCHER.contentMatcher(existingMeal));

        mockMvc.perform(get(REST_PATH + nonExistentMeal.getId()).with(userAccount))
                .andExpect(status().isNotFound());

        mockMvc.perform(get(REST_PATH + existingMeal.getId()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_PATH).with(userAccount))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(MEAL_WITH_EXCESS_MATCHER.contentMatcher(mealsWithExcess));

        mockMvc.perform(get(REST_PATH))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetBetween() throws Exception {
        mockMvc.perform(get(REST_PATH + "between?from=" + from + "&to=" + to).with(userAccount))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(MEAL_WITH_EXCESS_MATCHER.contentMatcher(betweenMealsWithExcess));

        mockMvc.perform(get(REST_PATH + "between?from=" + "&to=").with(userAccount))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get(REST_PATH + "between?from=" + from + "&to=" + to))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testRemove() throws Exception {

    }
}